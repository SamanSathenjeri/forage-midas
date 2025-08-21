package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.TransactionRecordId;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConduit {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public DatabaseConduit(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public UserRecord getUserById(long id){
        Optional<UserRecord> userOptional = userRepository.findById(id);
        
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }

    public TransactionRecord getTransactionRecordById(TransactionRecordId transactionRecordId){
        Optional<TransactionRecord> transactionOptional = transactionRepository.findById(transactionRecordId);
        
        if (transactionOptional.isPresent()) {
            return transactionOptional.get();
        } else {
            return null;
        }
    }

    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

    public void save(TransactionRecord transactionRecord){
        transactionRepository.save(transactionRecord);
    }

    public void printUserDatabase(){
        List<UserRecord> users = (List<UserRecord>) userRepository.findAll();
        for (UserRecord user : users) {
            System.out.println(user);
        }
        System.out.println('\n' + "----------------------" + '\n');
    }
}
