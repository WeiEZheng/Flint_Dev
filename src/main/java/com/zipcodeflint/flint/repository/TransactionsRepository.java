package com.zipcodeflint.flint.repository;

import com.zipcodeflint.flint.domain.Transactions;
import com.zipcodeflint.flint.domain.enumeration.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Spring Data SQL repository for the Transactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    Page<Transactions> findByDateOfTransaction(Instant dateOfTransaction, Pageable pageable);

    Page<Transactions> findByDateOfTransactionIsBetween(Instant dateOfTransactionStart, Instant dateOfTransactionEnd, Pageable pageable);

    Page<Transactions> findByFromAccountNumber(String fromAccountNumber, Pageable pageable);

    Page<Transactions> findByToAccountNumber(String toAccountNumber, Pageable pageable);

    Page<Transactions> findByTypeOfTransaction(TransactionType typeOfTransaction, Pageable pageable);

    Page<Transactions> findByTransactionAmountIsBetween(BigDecimal transactionAmountStart,
                                                        BigDecimal transactionAmountEnd, Pageable pageable);

    Page<Transactions> findByTransactionAmountIsGreaterThanEqual(BigDecimal transactionAmount, Pageable pageable);

    Page<Transactions> findByTransactionAmountLessThanEqual(BigDecimal transactionAmount, Pageable pageable);
}
