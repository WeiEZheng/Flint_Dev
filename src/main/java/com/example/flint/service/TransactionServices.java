package com.example.flint.service;

import com.example.flint.model.Transaction;
import com.example.flint.model.enumeration.TransactionType;
import com.example.flint.repository.BankAccountRepository;
import com.example.flint.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServices {
    private final TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public TransactionServices(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction) {
        transaction.setDateOfTransaction(Instant.now());

        if (transaction.getTypeOfTransaction().equals(TransactionType.TRANSFER)){
            Transaction transactionCopy = new Transaction();
            transaction.setTypeOfTransaction(TransactionType.DEBIT);
            transactionCopy.setTypeOfTransaction(TransactionType.CREDIT);
            transactionCopy.setFromAccountNumber(transaction.getToAccountNumber());
            transactionCopy.setToAccountNumber(transaction.getFromAccountNumber());
            transactionCopy.setDateOfTransaction(transaction.getDateOfTransaction());
            transactionCopy.setCategory(transaction.getCategory());
            transactionCopy.setTransactionAmount(transaction.getTransactionAmount());
            transactionRepository.save(transactionCopy);
        }
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

    public List<Transaction> findByDateOfTransaction(
            Long accountId,
            Instant dateOfTransaction) {
        return transactionRepository.findByBankAccount_IdAndDateOfTransaction(
                accountId,
                dateOfTransaction);
    }

    public List<Transaction> findByDateOfTransactionIsBetween(
            Long accountId,
            Instant dateOfTransactionStart,
            Instant dateOfTransactionEnd) {
        return transactionRepository.findByBankAccount_IdAndDateOfTransactionIsBetween(
                accountId,
                dateOfTransactionStart,
                dateOfTransactionEnd);
    }

    public List<Transaction> findByFromAccountNumber(Long fromAccountNumber) {
        return transactionRepository.findByFromAccountNumber(fromAccountNumber);
    }

    public List<Transaction> findByToAccountNumber(Long toAccountNumber) {
        return transactionRepository.findByToAccountNumber(toAccountNumber);
    }

    public List<Transaction> findByTypeOfTransaction(
            Long id,
            TransactionType typeOfTransaction) {
        return transactionRepository.findByBankAccount_IdAndTypeOfTransaction(
                id,
                typeOfTransaction);
    }

    public List<Transaction> findByTransactionAmountIsBetween(Long id,
                                                              BigDecimal transactionAmountStart,
                                                              BigDecimal transactionAmountEnd) {
        return transactionRepository.findByBankAccount_IdAndTransactionAmountIsBetween(
                id,
                transactionAmountStart,
                transactionAmountEnd);
    }

    public List<Transaction> findByTransactionAmountIsGreaterThanEqual(
            Long id,
            BigDecimal transactionAmount) {
        return transactionRepository.findByBankAccount_IdAndTransactionAmountIsGreaterThanEqual(id, transactionAmount);
    }

    public List<Transaction> findByTransactionAmountLessThanEqual(
            Long bankId,
            BigDecimal transactionAmount) {
        return transactionRepository.findByBankAccount_IdAndTransactionAmountLessThanEqual(
                bankId,
                transactionAmount);
    }

    public List<Transaction> findByCategory_Id(
            Long bankId,
            Long categoryId) {
        return transactionRepository.findByBankAccount_IdAndCategory_Id(
                bankId,
                categoryId);
    }
}
