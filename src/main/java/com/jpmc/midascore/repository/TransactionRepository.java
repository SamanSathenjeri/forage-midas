package com.jpmc.midascore.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.TransactionRecordId;

public interface TransactionRepository extends CrudRepository<TransactionRecord, TransactionRecordId>{
    Optional<TransactionRecord> findById(TransactionRecordId id);
}
