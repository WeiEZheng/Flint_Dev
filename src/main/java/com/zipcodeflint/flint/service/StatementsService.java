package com.zipcodeflint.flint.service;

import com.zipcodeflint.flint.domain.Statements;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Statements}.
 */
public interface StatementsService {
    /**
     * Save a statements.
     *
     * @param statements the entity to save.
     * @return the persisted entity.
     */
    Statements save(Statements statements);

    /**
     * Updates a statements.
     *
     * @param statements the entity to update.
     * @return the persisted entity.
     */
    Statements update(Statements statements);

    /**
     * Partially updates a statements.
     *
     * @param statements the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Statements> partialUpdate(Statements statements);

    /**
     * Get all the statements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Statements> findAll(Pageable pageable);

    /**
     * Get all the statements with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Statements> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" statements.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Statements> findOne(Long id);

    /**
     * Delete the "id" statements.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
