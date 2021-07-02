package com.samuraiiway.sleuthjdbc.controller;

import brave.Tracer;
import com.samuraiiway.sleuthjdbc.controller.model.TransactionModel;
import com.samuraiiway.sleuthjdbc.entity.TransactionEntity;
import com.samuraiiway.sleuthjdbc.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private Tracer tracer;

    @GetMapping("/transaction/{id}")
    public ResponseEntity getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(
                TransactionModel.from(
                        transactionService.findOneById(id)
                )
        );
    }

    @PostMapping("/transaction")
    public ResponseEntity createTransaction(@RequestBody TransactionModel request) {
        return ResponseEntity.ok(
                transactionService.create(request.toEntity())
        );
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity createTransaction(@PathVariable Long id, @RequestBody TransactionModel request) {
        return ResponseEntity.ok(
                transactionService.update(request.toEntity(id))
        );
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity createTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(
                TransactionModel.from(
                        transactionService.delete(id)
                )
        );
    }
}
