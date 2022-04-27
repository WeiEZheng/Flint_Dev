package com.zipcodeflint.flint.service.impl;

import com.zipcodeflint.flint.domain.Statements;
import com.zipcodeflint.flint.repository.StatementsRepository;
import com.zipcodeflint.flint.service.StatementsService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Statements}.
 */
@Service
@Transactional
public class StatementsServiceImpl implements StatementsService {

    private final Logger log = LoggerFactory.getLogger(StatementsServiceImpl.class);

    private final StatementsRepository statementsRepository;

    public StatementsServiceImpl(StatementsRepository statementsRepository) {
        this.statementsRepository = statementsRepository;
    }

    @Override
    public Statements save(Statements statements) {
        log.debug("Request to save Statements : {}", statements);
        return statementsRepository.save(statements);
    }

    @Override
    public Statements update(Statements statements) {
        log.debug("Request to save Statements : {}", statements);
        return statementsRepository.save(statements);
    }

    @Override
    public Optional<Statements> partialUpdate(Statements statements) {
        log.debug("Request to partially update Statements : {}", statements);

        return statementsRepository
            .findById(statements.getId())
            .map(existingStatements -> {
                if (statements.getStartDate() != null) {
                    existingStatements.setStartDate(statements.getStartDate());
                }
                if (statements.getEndDate() != null) {
                    existingStatements.setEndDate(statements.getEndDate());
                }

                return existingStatements;
            })
            .map(statementsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Statements> findAll(Pageable pageable) {
        log.debug("Request to get all Statements");
        return statementsRepository.findAll(pageable);
    }

    public Page<Statements> findAllWithEagerRelationships(Pageable pageable) {
        return statementsRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Statements> findOne(Long id) {
        log.debug("Request to get Statements : {}", id);
        return statementsRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Statements : {}", id);
        statementsRepository.deleteById(id);
    }
}
