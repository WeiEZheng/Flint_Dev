package com.zipcodeflint.flint.service.impl;

import com.zipcodeflint.flint.domain.Transactions;
import com.zipcodeflint.flint.repository.TransactionsRepository;
import com.zipcodeflint.flint.service.TransactionsService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Transactions}.
 */
@Service
@Transactional
public class TransactionsServiceImpl implements TransactionsService {

    private final Logger log = LoggerFactory.getLogger(TransactionsServiceImpl.class);

    private final TransactionsRepository transactionsRepository;

    public TransactionsServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public Transactions save(Transactions transactions) {
        log.debug("Request to save Transactions : {}", transactions);
        return transactionsRepository.save(transactions);
    }

    @Override
    public Transactions update(Transactions transactions) {
        log.debug("Request to save Transactions : {}", transactions);
        return transactionsRepository.save(transactions);
    }

    @Override
    public Optional<Transactions> partialUpdate(Transactions transactions) {
        log.debug("Request to partially update Transactions : {}", transactions);

        return transactionsRepository
            .findById(transactions.getId())
            .map(existingTransactions -> {
                if (transactions.getDateOfTransaction() != null) {
                    existingTransactions.setDateOfTransaction(transactions.getDateOfTransaction());
                }
                if (transactions.getTypeOfTransaction() != null) {
                    existingTransactions.setTypeOfTransaction(transactions.getTypeOfTransaction());
                }
                if (transactions.getTransactionAmount() != null) {
                    existingTransactions.setTransactionAmount(transactions.getTransactionAmount());
                }
                if (transactions.getToAccountNumber() != null) {
                    existingTransactions.setToAccountNumber(transactions.getToAccountNumber());
                }
                if (transactions.getFromAccountNumber() != null) {
                    existingTransactions.setFromAccountNumber(transactions.getFromAccountNumber());
                }

                return existingTransactions;
            })
            .map(transactionsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Transactions> findAll(Pageable pageable) {
        log.debug("Request to get all Transactions");
        return transactionsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transactions> findOne(Long id) {
        log.debug("Request to get Transactions : {}", id);
        return transactionsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transactions : {}", id);
        transactionsRepository.deleteById(id);
    }
}
