package com.example.flint.controller;

import com.example.flint.model.Transaction;
import com.example.flint.repository.TransactionRepository;
import com.example.flint.service.TransactionServices;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionServices transactionServices;

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionServices transactionServices, TransactionRepository transactionRepository) {
        this.transactionServices = transactionServices;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransactions(@Validated @RequestBody Transaction transaction) throws URISyntaxException {
        if (transaction.getId() != null) {
//            need to throw exception for existing entity
        }
        Transaction result = transactionServices.save(transaction);
        return ResponseEntity
                .created(new URI("/api/transactions/" + result.getId()))
                .header("message here")
                .body(result);
    }
}
