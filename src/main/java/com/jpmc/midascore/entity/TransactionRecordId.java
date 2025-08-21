package com.jpmc.midascore.entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class TransactionRecordId implements Serializable{
    private long senderId;
    private long recipientId;

    public TransactionRecordId(){
    }
    
    public TransactionRecordId(long senderId, long recipientId){
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRecordId that = (TransactionRecordId) o;
        return senderId == that.senderId && recipientId == that.recipientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderId, recipientId);
    }

    public long getSenderId(){
        return senderId;
    }

    public long getRecipientId(){
        return recipientId;
    }

    public void setSenderId(long senderId){
        this.senderId = senderId;
    }

    public void setRecipientId(long recipientId){
        this.recipientId = recipientId;
    }
}
