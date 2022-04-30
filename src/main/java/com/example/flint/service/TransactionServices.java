package com.example.flint.service;

import com.example.flint.model.Transaction;
import com.example.flint.model.enumeration.TransactionType;
import com.example.flint.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServices {
    private final TransactionRepository transactionRepository;

    public TransactionServices(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction update(Transaction transaction) {
        return this.save(transaction);
    }

    public Optional<Transaction> partialUpdate(Transaction transaction) {
        return transactionRepository
                .findById(transaction.getId())
                .map(existingTransactions -> {
                    if (transaction.getDateOfTransaction() != null) {
                        existingTransactions.setDateOfTransaction(transaction.getDateOfTransaction());
                    }
                    if (transaction.getTypeOfTransaction() != null) {
                        existingTransactions.setTypeOfTransaction(transaction.getTypeOfTransaction());
                    }
                    if (transaction.getTransactionAmount() != null) {
                        existingTransactions.setTransactionAmount(transaction.getTransactionAmount());
                    }
                    if (transaction.getToAccountNumber() != null) {
                        existingTransactions.setToAccountNumber(transaction.getToAccountNumber());
                    }
                    if (transaction.getFromAccountNumber() != null) {
                        existingTransactions.setFromAccountNumber(transaction.getFromAccountNumber());
                    }

                    return existingTransactions;
                })
                .map(this::save);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findOne(Long id) {
        return transactionRepository.findById(id);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> findByDateOfTransaction(Instant dateOfTransaction) {
        return transactionRepository.findByDateOfTransaction(dateOfTransaction);
    }

    public List<Transaction> findByDateOfTransactionIsBetween(Instant dateOfTransactionStart, Instant dateOfTransactionEnd) {
        return transactionRepository.findByDateOfTransactionIsBetween(dateOfTransactionStart,dateOfTransactionEnd);
    }

    public List<Transaction> findByFromAccountNumber(Long fromAccountNumber) {
        return transactionRepository.findByFromAccountNumber(fromAccountNumber);
    }

    public List<Transaction> findByToAccountNumber(Long toAccountNumber) {
        return transactionRepository.findByToAccountNumber(toAccountNumber);
    }

    public List<Transaction> findByTypeOfTransaction(TransactionType typeOfTransaction) {
        return transactionRepository.findByTypeOfTransaction(typeOfTransaction);
    }

    public List<Transaction> findByTransactionAmountIsBetween(BigDecimal transactionAmountStart,
                                                              BigDecimal transactionAmountEnd) {
        return transactionRepository.findByTransactionAmountIsBetween(transactionAmountStart,transactionAmountEnd);
    }

    public List<Transaction> findByTransactionAmountIsGreaterThanEqual(BigDecimal transactionAmount) {
        return transactionRepository.findByTransactionAmountIsGreaterThanEqual(transactionAmount);
    }

    public List<Transaction> findByTransactionAmountLessThanEqual(BigDecimal transactionAmount) {
        return transactionRepository.findByTransactionAmountLessThanEqual(transactionAmount);
    }
}
