package com.jpmc.midascore;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.component.DatabaseConduit;
import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class TransactionListener {

    @Autowired
    private DatabaseConduit databaseConduit;

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-core-group")
    public void listen(Transaction transaction){
        // Split transaction into Sender, Receiver, amount
        long senderId = transaction.getSenderId();
        long recipientId = transaction.getRecipientId();
        float amount = transaction.getAmount();

        // find sender and find receiver and check if present
        UserRecord sender = databaseConduit.getUserById(senderId);
        UserRecord receiver = databaseConduit.getUserById(recipientId);

        if (sender != null && receiver != null){
            //checks if sender has enough balance; if not, ignore
            if(sender.getBalance() >= amount){
                // checking validating 
                RestTemplate restTemplate = new RestTemplate();
                String ApiUrl = "http://localhost:8080/incentive";
                ResponseEntity<Incentive> response = restTemplate.postForEntity(ApiUrl, transaction, Incentive.class);
                long incentiveAmount = 0;
                
                if (response.getStatusCode().is2xxSuccessful()) {
                    Incentive incentive = response.getBody();
                    incentiveAmount = incentive.getAmount();
                }

                // create new Transaction Record
                TransactionRecord transactionRecord = new TransactionRecord(senderId, recipientId, amount, incentiveAmount);
                databaseConduit.save(transactionRecord);

                sender.setBalance(sender.getBalance()-amount);
                receiver.setBalance(receiver.getBalance()+amount+incentiveAmount);
                databaseConduit.save(sender);
                databaseConduit.save(receiver);
            }
        }

        databaseConduit.printUserDatabase();
    }
}
