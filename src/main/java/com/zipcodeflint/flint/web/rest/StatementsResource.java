package com.zipcodeflint.flint.web.rest;

import com.zipcodeflint.flint.domain.Statements;
import com.zipcodeflint.flint.repository.StatementsRepository;
import com.zipcodeflint.flint.service.StatementsService;
import com.zipcodeflint.flint.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.zipcodeflint.flint.domain.Statements}.
 */
@RestController
@RequestMapping("/api")
public class StatementsResource {

    private final Logger log = LoggerFactory.getLogger(StatementsResource.class);

    private static final String ENTITY_NAME = "statements";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatementsService statementsService;

    private final StatementsRepository statementsRepository;

    public StatementsResource(StatementsService statementsService, StatementsRepository statementsRepository) {
        this.statementsService = statementsService;
        this.statementsRepository = statementsRepository;
    }

    /**
     * {@code POST  /statements} : Create a new statements.
     *
     * @param statements the statements to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statements, or with status {@code 400 (Bad Request)} if the statements has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/statements")
    public ResponseEntity<Statements> createStatements(@Valid @RequestBody Statements statements) throws URISyntaxException {
        log.debug("REST request to save Statements : {}", statements);
        if (statements.getId() != null) {
            throw new BadRequestAlertException("A new statements cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Statements result = statementsService.save(statements);
        return ResponseEntity
            .created(new URI("/api/statements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /statements/:id} : Updates an existing statements.
     *
     * @param id the id of the statements to save.
     * @param statements the statements to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statements,
     * or with status {@code 400 (Bad Request)} if the statements is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statements couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/statements/{id}")
    public ResponseEntity<Statements> updateStatements(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Statements statements
    ) throws URISyntaxException {
        log.debug("REST request to update Statements : {}, {}", id, statements);
        if (statements.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, statements.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!statementsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Statements result = statementsService.update(statements);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, statements.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /statements/:id} : Partial updates given fields of an existing statements, field will ignore if it is null
     *
     * @param id the id of the statements to save.
     * @param statements the statements to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statements,
     * or with status {@code 400 (Bad Request)} if the statements is not valid,
     * or with status {@code 404 (Not Found)} if the statements is not found,
     * or with status {@code 500 (Internal Server Error)} if the statements couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/statements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Statements> partialUpdateStatements(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Statements statements
    ) throws URISyntaxException {
        log.debug("REST request to partial update Statements partially : {}, {}", id, statements);
        if (statements.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, statements.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!statementsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Statements> result = statementsService.partialUpdate(statements);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, statements.getId().toString())
        );
    }

    /**
     * {@code GET  /statements} : get all the statements.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statements in body.
     */
    @GetMapping("/statements")
    public ResponseEntity<List<Statements>> getAllStatements(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Statements");
        Page<Statements> page;
        if (eagerload) {
            page = statementsService.findAllWithEagerRelationships(pageable);
        } else {
            page = statementsService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /statements/:id} : get the "id" statements.
     *
     * @param id the id of the statements to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statements, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statements/{id}")
    public ResponseEntity<Statements> getStatements(@PathVariable Long id) {
        log.debug("REST request to get Statements : {}", id);
        Optional<Statements> statements = statementsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statements);
    }

    /**
     * {@code DELETE  /statements/:id} : delete the "id" statements.
     *
     * @param id the id of the statements to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/statements/{id}")
    public ResponseEntity<Void> deleteStatements(@PathVariable Long id) {
        log.debug("REST request to delete Statements : {}", id);
        statementsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
