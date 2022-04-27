package com.zipcodeflint.flint.repository;

import com.zipcodeflint.flint.domain.Transactions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Transactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {}
