package com.jpmc.midascore.entity;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_records")
public class TransactionRecord {

    @EmbeddedId
    private TransactionRecordId id;

    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private float incentive;

    public TransactionRecord() {
    }

    public TransactionRecord(long senderId, long recipientId, float amount, float incentive) {
        this.id = new TransactionRecordId(senderId, recipientId);
        this.amount = amount;
    }

    public long getSenderId() {
        return this.id.getSenderId();
    }

    public void setSenderId(long senderId) {
        this.id.setSenderId(senderId);
    }

    public long getRecipientId() {
        return this.id.getRecipientId();
    }

    public void setRecipientId(long recipientId) {
        this.id.setRecipientId(recipientId);
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getIncentive() {
        return incentive;
    }

    public void setIncentive(float incentive) {
        this.incentive = incentive;
    }

    @Override
    public String toString() {
        return "Transaction {senderId=" + this.getSenderId() + ", recipientId=" + this.getRecipientId() + ", amount=" + amount + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRecord that = (TransactionRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
