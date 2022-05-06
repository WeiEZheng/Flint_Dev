package com.example.flint.repository;

import com.example.flint.model.Category;
import com.example.flint.model.Transaction;
import com.example.flint.model.User;
import com.example.flint.model.enumeration.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccountNumberAndDateOfTransaction(Long id, Instant dateOfTransaction);

    List<Transaction> findByFromAccountNumberAndDateOfTransactionIsBetween(
            Long id,
            Instant dateOfTransactionStart,
            Instant dateOfTransactionEnd);

    List<Transaction> findByFromAccountNumber(Long fromAccountNumber);

    List<Transaction> findByToAccountNumber(Long toAccountNumber);

    List<Transaction> findByFromAccountNumberAndTypeOfTransaction(Long id, TransactionType typeOfTransaction);

    List<Transaction> findByFromAccountNumberAndTransactionAmountIsBetween(
            Long id,
            BigDecimal transactionAmountStart,
            BigDecimal transactionAmountEnd);

    List<Transaction> findByFromAccountNumberAndTransactionAmountIsGreaterThanEqual(Long id, BigDecimal transactionAmount);

    List<Transaction> findByFromAccountNumberAndTransactionAmountLessThanEqual(Long id, BigDecimal transactionAmount);

    List<Transaction> findByFromAccountNumberAndCategory_Id(Long id, Long id1);

}
