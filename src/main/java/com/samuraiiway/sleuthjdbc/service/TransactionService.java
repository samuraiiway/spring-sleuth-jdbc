package com.samuraiiway.sleuthjdbc.service;

import com.samuraiiway.sleuthjdbc.entity.TransactionEntity;
import com.samuraiiway.sleuthjdbc.entity.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionEntity findOneById(Long id) {
        return transactionRepository.getOne(id);
    }

    public TransactionEntity create(TransactionEntity entity) {
        return transactionRepository.save(entity);
    }

    public TransactionEntity update(TransactionEntity entity) {
        return transactionRepository.save(entity);
    }

    public TransactionEntity delete(Long id) {
        TransactionEntity entity = findOneById(id);

        if (entity != null) {
            transactionRepository.delete(entity);
        }

        return entity;
    }
}
