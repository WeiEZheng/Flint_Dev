package com.example.flint.repository;

import com.example.flint.model.Transaction;
import com.example.flint.model.enumeration.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateOfTransaction(Instant dateOfTransaction);

    List<Transaction> findByDateOfTransactionIsBetween(Instant dateOfTransactionStart,
                                                       Instant dateOfTransactionEnd);

    List<Transaction> findByFromAccountNumber(Long fromAccountNumber);

    List<Transaction> findByToAccountNumber(Long toAccountNumber);

    List<Transaction> findByTypeOfTransaction(TransactionType typeOfTransaction);

    List<Transaction> findByTransactionAmountIsBetween(BigDecimal transactionAmountStart,
                                                       BigDecimal transactionAmountEnd);

    List<Transaction> findByTransactionAmountIsGreaterThanEqual(BigDecimal transactionAmount);

    List<Transaction> findByTransactionAmountLessThanEqual(BigDecimal transactionAmount);
}
