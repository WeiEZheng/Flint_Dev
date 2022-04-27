package com.zipcodeflint.flint.web.rest;

import static com.zipcodeflint.flint.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.zipcodeflint.flint.IntegrationTest;
import com.zipcodeflint.flint.domain.Transactions;
import com.zipcodeflint.flint.domain.enumeration.TransactionType;
import com.zipcodeflint.flint.repository.TransactionsRepository;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TransactionsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TransactionsResourceIT {

    private static final Instant DEFAULT_DATE_OF_TRANSACTION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF_TRANSACTION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final TransactionType DEFAULT_TYPE_OF_TRANSACTION = TransactionType.DEBIT;
    private static final TransactionType UPDATED_TYPE_OF_TRANSACTION = TransactionType.CREDIT;

    private static final BigDecimal DEFAULT_TRANSACTION_AMOUNT = new BigDecimal(0);
    private static final BigDecimal UPDATED_TRANSACTION_AMOUNT = new BigDecimal(1);

    private static final String DEFAULT_TO_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TO_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FROM_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FROM_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionsMockMvc;

    private Transactions transactions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transactions createEntity(EntityManager em) {
        Transactions transactions = new Transactions()
            .dateOfTransaction(DEFAULT_DATE_OF_TRANSACTION)
            .typeOfTransaction(DEFAULT_TYPE_OF_TRANSACTION)
            .transactionAmount(DEFAULT_TRANSACTION_AMOUNT)
            .toAccountNumber(DEFAULT_TO_ACCOUNT_NUMBER)
            .fromAccountNumber(DEFAULT_FROM_ACCOUNT_NUMBER);
        return transactions;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transactions createUpdatedEntity(EntityManager em) {
        Transactions transactions = new Transactions()
            .dateOfTransaction(UPDATED_DATE_OF_TRANSACTION)
            .typeOfTransaction(UPDATED_TYPE_OF_TRANSACTION)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .toAccountNumber(UPDATED_TO_ACCOUNT_NUMBER)
            .fromAccountNumber(UPDATED_FROM_ACCOUNT_NUMBER);
        return transactions;
    }

    @BeforeEach
    public void initTest() {
        transactions = createEntity(em);
    }

    @Test
    @Transactional
    void createTransactions() throws Exception {
        int databaseSizeBeforeCreate = transactionsRepository.findAll().size();
        // Create the Transactions
        restTransactionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isCreated());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeCreate + 1);
        Transactions testTransactions = transactionsList.get(transactionsList.size() - 1);
        assertThat(testTransactions.getDateOfTransaction()).isEqualTo(DEFAULT_DATE_OF_TRANSACTION);
        assertThat(testTransactions.getTypeOfTransaction()).isEqualTo(DEFAULT_TYPE_OF_TRANSACTION);
        assertThat(testTransactions.getTransactionAmount()).isEqualByComparingTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testTransactions.getToAccountNumber()).isEqualTo(DEFAULT_TO_ACCOUNT_NUMBER);
        assertThat(testTransactions.getFromAccountNumber()).isEqualTo(DEFAULT_FROM_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    void createTransactionsWithExistingId() throws Exception {
        // Create the Transactions with an existing ID
        transactions.setId(1L);

        int databaseSizeBeforeCreate = transactionsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDateOfTransactionIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionsRepository.findAll().size();
        // set the field null
        transactions.setDateOfTransaction(null);

        // Create the Transactions, which fails.

        restTransactionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeOfTransactionIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionsRepository.findAll().size();
        // set the field null
        transactions.setTypeOfTransaction(null);

        // Create the Transactions, which fails.

        restTransactionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFromAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionsRepository.findAll().size();
        // set the field null
        transactions.setFromAccountNumber(null);

        // Create the Transactions, which fails.

        restTransactionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTransactions() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList
        restTransactionsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactions.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateOfTransaction").value(hasItem(DEFAULT_DATE_OF_TRANSACTION.toString())))
            .andExpect(jsonPath("$.[*].typeOfTransaction").value(hasItem(DEFAULT_TYPE_OF_TRANSACTION.toString())))
            .andExpect(jsonPath("$.[*].transactionAmount").value(hasItem(sameNumber(DEFAULT_TRANSACTION_AMOUNT))))
            .andExpect(jsonPath("$.[*].toAccountNumber").value(hasItem(DEFAULT_TO_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].fromAccountNumber").value(hasItem(DEFAULT_FROM_ACCOUNT_NUMBER)));
    }

    @Test
    @Transactional
    void getTransactions() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get the transactions
        restTransactionsMockMvc
            .perform(get(ENTITY_API_URL_ID, transactions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transactions.getId().intValue()))
            .andExpect(jsonPath("$.dateOfTransaction").value(DEFAULT_DATE_OF_TRANSACTION.toString()))
            .andExpect(jsonPath("$.typeOfTransaction").value(DEFAULT_TYPE_OF_TRANSACTION.toString()))
            .andExpect(jsonPath("$.transactionAmount").value(sameNumber(DEFAULT_TRANSACTION_AMOUNT)))
            .andExpect(jsonPath("$.toAccountNumber").value(DEFAULT_TO_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.fromAccountNumber").value(DEFAULT_FROM_ACCOUNT_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingTransactions() throws Exception {
        // Get the transactions
        restTransactionsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTransactions() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();

        // Update the transactions
        Transactions updatedTransactions = transactionsRepository.findById(transactions.getId()).get();
        // Disconnect from session so that the updates on updatedTransactions are not directly saved in db
        em.detach(updatedTransactions);
        updatedTransactions
            .dateOfTransaction(UPDATED_DATE_OF_TRANSACTION)
            .typeOfTransaction(UPDATED_TYPE_OF_TRANSACTION)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .toAccountNumber(UPDATED_TO_ACCOUNT_NUMBER)
            .fromAccountNumber(UPDATED_FROM_ACCOUNT_NUMBER);

        restTransactionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTransactions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTransactions))
            )
            .andExpect(status().isOk());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
        Transactions testTransactions = transactionsList.get(transactionsList.size() - 1);
        assertThat(testTransactions.getDateOfTransaction()).isEqualTo(UPDATED_DATE_OF_TRANSACTION);
        assertThat(testTransactions.getTypeOfTransaction()).isEqualTo(UPDATED_TYPE_OF_TRANSACTION);
        assertThat(testTransactions.getTransactionAmount()).isEqualByComparingTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testTransactions.getToAccountNumber()).isEqualTo(UPDATED_TO_ACCOUNT_NUMBER);
        assertThat(testTransactions.getFromAccountNumber()).isEqualTo(UPDATED_FROM_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingTransactions() throws Exception {
        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();
        transactions.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transactions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTransactions() throws Exception {
        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();
        transactions.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTransactions() throws Exception {
        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();
        transactions.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTransactionsWithPatch() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();

        // Update the transactions using partial update
        Transactions partialUpdatedTransactions = new Transactions();
        partialUpdatedTransactions.setId(transactions.getId());

        partialUpdatedTransactions
            .dateOfTransaction(UPDATED_DATE_OF_TRANSACTION)
            .typeOfTransaction(UPDATED_TYPE_OF_TRANSACTION)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .toAccountNumber(UPDATED_TO_ACCOUNT_NUMBER)
            .fromAccountNumber(UPDATED_FROM_ACCOUNT_NUMBER);

        restTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransactions.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTransactions))
            )
            .andExpect(status().isOk());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
        Transactions testTransactions = transactionsList.get(transactionsList.size() - 1);
        assertThat(testTransactions.getDateOfTransaction()).isEqualTo(UPDATED_DATE_OF_TRANSACTION);
        assertThat(testTransactions.getTypeOfTransaction()).isEqualTo(UPDATED_TYPE_OF_TRANSACTION);
        assertThat(testTransactions.getTransactionAmount()).isEqualByComparingTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testTransactions.getToAccountNumber()).isEqualTo(UPDATED_TO_ACCOUNT_NUMBER);
        assertThat(testTransactions.getFromAccountNumber()).isEqualTo(UPDATED_FROM_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateTransactionsWithPatch() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();

        // Update the transactions using partial update
        Transactions partialUpdatedTransactions = new Transactions();
        partialUpdatedTransactions.setId(transactions.getId());

        partialUpdatedTransactions
            .dateOfTransaction(UPDATED_DATE_OF_TRANSACTION)
            .typeOfTransaction(UPDATED_TYPE_OF_TRANSACTION)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .toAccountNumber(UPDATED_TO_ACCOUNT_NUMBER)
            .fromAccountNumber(UPDATED_FROM_ACCOUNT_NUMBER);

        restTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransactions.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTransactions))
            )
            .andExpect(status().isOk());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
        Transactions testTransactions = transactionsList.get(transactionsList.size() - 1);
        assertThat(testTransactions.getDateOfTransaction()).isEqualTo(UPDATED_DATE_OF_TRANSACTION);
        assertThat(testTransactions.getTypeOfTransaction()).isEqualTo(UPDATED_TYPE_OF_TRANSACTION);
        assertThat(testTransactions.getTransactionAmount()).isEqualByComparingTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testTransactions.getToAccountNumber()).isEqualTo(UPDATED_TO_ACCOUNT_NUMBER);
        assertThat(testTransactions.getFromAccountNumber()).isEqualTo(UPDATED_FROM_ACCOUNT_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingTransactions() throws Exception {
        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();
        transactions.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, transactions.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTransactions() throws Exception {
        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();
        transactions.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transactions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTransactions() throws Exception {
        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();
        transactions.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(transactions))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTransactions() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        int databaseSizeBeforeDelete = transactionsRepository.findAll().size();

        // Delete the transactions
        restTransactionsMockMvc
            .perform(delete(ENTITY_API_URL_ID, transactions.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
