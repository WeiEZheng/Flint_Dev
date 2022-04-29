package com.zipcodeflint.flint.web.rest;

import com.zipcodeflint.flint.domain.Transactions;
import com.zipcodeflint.flint.domain.enumeration.TransactionType;
import com.zipcodeflint.flint.repository.TransactionsRepository;
import com.zipcodeflint.flint.service.TransactionsService;
import com.zipcodeflint.flint.web.rest.errors.BadRequestAlertException;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
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
 * REST controller for managing {@link com.zipcodeflint.flint.domain.Transactions}.
 */
@RestController
@RequestMapping("/api")
public class TransactionsResource {

    private final Logger log = LoggerFactory.getLogger(TransactionsResource.class);

    private static final String ENTITY_NAME = "transactions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionsService transactionsService;

    private final TransactionsRepository transactionsRepository;

    public TransactionsResource(TransactionsService transactionsService, TransactionsRepository transactionsRepository) {
        this.transactionsService = transactionsService;
        this.transactionsRepository = transactionsRepository;
    }

    /**
     * {@code POST  /transactions} : Create a new transactions.
     *
     * @param transactions the transactions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactions, or with status {@code 400 (Bad Request)} if the transactions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transactions")
    public ResponseEntity<Transactions> createTransactions(@Valid @RequestBody Transactions transactions) throws URISyntaxException {
        log.debug("REST request to save Transactions : {}", transactions);
        if (transactions.getId() != null) {
            throw new BadRequestAlertException("A new transactions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Transactions result = transactionsService.save(transactions);
        return ResponseEntity
            .created(new URI("/api/transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transactions/:id} : Updates an existing transactions.
     *
     * @param id the id of the transactions to save.
     * @param transactions the transactions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactions,
     * or with status {@code 400 (Bad Request)} if the transactions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transactions/{id}")
    public ResponseEntity<Transactions> updateTransactions(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Transactions transactions
    ) throws URISyntaxException {
        log.debug("REST request to update Transactions : {}, {}", id, transactions);
        if (transactions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transactions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transactionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Transactions result = transactionsService.update(transactions);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactions.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /transactions/:id} : Partial updates given fields of an existing transactions, field will ignore if it is null
     *
     * @param id the id of the transactions to save.
     * @param transactions the transactions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactions,
     * or with status {@code 400 (Bad Request)} if the transactions is not valid,
     * or with status {@code 404 (Not Found)} if the transactions is not found,
     * or with status {@code 500 (Internal Server Error)} if the transactions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/transactions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Transactions> partialUpdateTransactions(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Transactions transactions
    ) throws URISyntaxException {
        log.debug("REST request to partial update Transactions partially : {}, {}", id, transactions);
        if (transactions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transactions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transactionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Transactions> result = transactionsService.partialUpdate(transactions);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactions.getId().toString())
        );
    }

    /**
     * {@code GET  /transactions} : get all the transactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactions in body.
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<Transactions>> getAllTransactions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Transactions");
        Page<Transactions> page = transactionsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /transactions/:id} : get the "id" transactions.
     *
     * @param id the id of the transactions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transactions> getTransactions(@PathVariable Long id) {
        log.debug("REST request to get Transactions : {}", id);
        Optional<Transactions> transactions = transactionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactions);
    }

    /**
     * {@code DELETE  /transactions/:id} : delete the "id" transactions.
     *
     * @param id the id of the transactions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransactions(@PathVariable Long id) {
        log.debug("REST request to delete Transactions : {}", id);
        transactionsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/transactions/findByDate")
    public ResponseEntity<List<Transactions>> findByDateOfTransaction(@org.springdoc.api.annotations.ParameterObject Pageable pageable,
                                                                      @RequestParam(value = "exact", required = false) Instant dateOfTransaction,
                                                                      @RequestParam(value = "start", required = false) Instant dateOfTransactionStart,
                                                                      @RequestParam(value = "end", required = false) Instant dateOfTransactionEnd) {
        log.debug("REST request to get a page of Transactions by date");
        Page<Transactions> page;
        if (dateOfTransaction != null) {
            page = transactionsService.findByDateOfTransaction(dateOfTransactionStart, pageable);
        } else if (dateOfTransactionStart != null && dateOfTransactionEnd != null){
            page = transactionsService.findByDateOfTransactionIsBetween(dateOfTransactionStart, dateOfTransactionEnd, pageable);
        } else {
            page = transactionsService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<List<Transactions>> getTransactionsByAccount(@org.springdoc.api.annotations.ParameterObject Pageable pageable,
                                                                @PathVariable String accountNumber,
                                                                @RequestParam(value = "type", required = true) String typeOfRequest) {
        log.debug("REST request to get a page of Transactions by account");
        Page<Transactions> page;
        if (typeOfRequest.equalsIgnoreCase("to"))
            page = transactionsService.findByToAccountNumber(accountNumber, pageable);
        else
            page = transactionsService.findByFromAccountNumber(accountNumber, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/transactions/findByAmount")
    public ResponseEntity<List<Transactions>> getTransactionsByAmount(@org.springdoc.api.annotations.ParameterObject Pageable pageable,
                                                                      @RequestParam(value = "type", required = false) String typeOfRequest,
                                                                      @RequestParam(value = "start", required = false) BigDecimal transactionAmountStart,
                                                                      @RequestParam(value = "end", required = false) BigDecimal transactionAmountEnd) {
        log.debug("REST request to get a page of Transactions by amount");
        Page<Transactions> page;
        if (typeOfRequest.equalsIgnoreCase("between") &&
            transactionAmountStart != null && transactionAmountEnd != null) {
            page = transactionsService.findByTransactionAmountIsBetween(transactionAmountStart, transactionAmountStart, pageable);
        } else if (typeOfRequest.equalsIgnoreCase("more") && transactionAmountStart != null){
            page = transactionsService.findByTransactionAmountIsGreaterThanEqual(transactionAmountStart, pageable);
        }
        else if (typeOfRequest.equalsIgnoreCase("less") && transactionAmountEnd != null) {
            page = transactionsService.findByTransactionAmountIsGreaterThanEqual(transactionAmountStart, pageable);
        } else {
            page = transactionsService.findByTransactionAmountLessThanEqual(transactionAmountEnd, pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/transactions/{typeOfTransaction}")
    public ResponseEntity<List<Transactions>> getTransactionsByType(@org.springdoc.api.annotations.ParameterObject Pageable pageable,
                                                                    @PathVariable TransactionType typeOfTransaction) {
        log.debug("REST request to get a page of Transactions");
        Page<Transactions> page = transactionsService.findByTypeOfTransaction(typeOfTransaction, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
