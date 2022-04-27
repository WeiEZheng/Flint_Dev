package com.zipcodeflint.flint.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.zipcodeflint.flint.IntegrationTest;
import com.zipcodeflint.flint.domain.Statements;
import com.zipcodeflint.flint.repository.StatementsRepository;
import com.zipcodeflint.flint.service.StatementsService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StatementsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class StatementsResourceIT {

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/statements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StatementsRepository statementsRepository;

    @Mock
    private StatementsRepository statementsRepositoryMock;

    @Mock
    private StatementsService statementsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStatementsMockMvc;

    private Statements statements;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statements createEntity(EntityManager em) {
        Statements statements = new Statements().startDate(DEFAULT_START_DATE).endDate(DEFAULT_END_DATE);
        return statements;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statements createUpdatedEntity(EntityManager em) {
        Statements statements = new Statements().startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);
        return statements;
    }

    @BeforeEach
    public void initTest() {
        statements = createEntity(em);
    }

    @Test
    @Transactional
    void createStatements() throws Exception {
        int databaseSizeBeforeCreate = statementsRepository.findAll().size();
        // Create the Statements
        restStatementsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(statements)))
            .andExpect(status().isCreated());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeCreate + 1);
        Statements testStatements = statementsList.get(statementsList.size() - 1);
        assertThat(testStatements.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testStatements.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    void createStatementsWithExistingId() throws Exception {
        // Create the Statements with an existing ID
        statements.setId(1L);

        int databaseSizeBeforeCreate = statementsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatementsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(statements)))
            .andExpect(status().isBadRequest());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = statementsRepository.findAll().size();
        // set the field null
        statements.setStartDate(null);

        // Create the Statements, which fails.

        restStatementsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(statements)))
            .andExpect(status().isBadRequest());

        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = statementsRepository.findAll().size();
        // set the field null
        statements.setEndDate(null);

        // Create the Statements, which fails.

        restStatementsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(statements)))
            .andExpect(status().isBadRequest());

        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllStatements() throws Exception {
        // Initialize the database
        statementsRepository.saveAndFlush(statements);

        // Get all the statementsList
        restStatementsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statements.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllStatementsWithEagerRelationshipsIsEnabled() throws Exception {
        when(statementsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restStatementsMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(statementsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllStatementsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(statementsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restStatementsMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(statementsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getStatements() throws Exception {
        // Initialize the database
        statementsRepository.saveAndFlush(statements);

        // Get the statements
        restStatementsMockMvc
            .perform(get(ENTITY_API_URL_ID, statements.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(statements.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingStatements() throws Exception {
        // Get the statements
        restStatementsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewStatements() throws Exception {
        // Initialize the database
        statementsRepository.saveAndFlush(statements);

        int databaseSizeBeforeUpdate = statementsRepository.findAll().size();

        // Update the statements
        Statements updatedStatements = statementsRepository.findById(statements.getId()).get();
        // Disconnect from session so that the updates on updatedStatements are not directly saved in db
        em.detach(updatedStatements);
        updatedStatements.startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);

        restStatementsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedStatements.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedStatements))
            )
            .andExpect(status().isOk());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeUpdate);
        Statements testStatements = statementsList.get(statementsList.size() - 1);
        assertThat(testStatements.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testStatements.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    void putNonExistingStatements() throws Exception {
        int databaseSizeBeforeUpdate = statementsRepository.findAll().size();
        statements.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatementsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, statements.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(statements))
            )
            .andExpect(status().isBadRequest());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStatements() throws Exception {
        int databaseSizeBeforeUpdate = statementsRepository.findAll().size();
        statements.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStatementsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(statements))
            )
            .andExpect(status().isBadRequest());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStatements() throws Exception {
        int databaseSizeBeforeUpdate = statementsRepository.findAll().size();
        statements.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStatementsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(statements)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStatementsWithPatch() throws Exception {
        // Initialize the database
        statementsRepository.saveAndFlush(statements);

        int databaseSizeBeforeUpdate = statementsRepository.findAll().size();

        // Update the statements using partial update
        Statements partialUpdatedStatements = new Statements();
        partialUpdatedStatements.setId(statements.getId());

        restStatementsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStatements.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStatements))
            )
            .andExpect(status().isOk());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeUpdate);
        Statements testStatements = statementsList.get(statementsList.size() - 1);
        assertThat(testStatements.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testStatements.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    void fullUpdateStatementsWithPatch() throws Exception {
        // Initialize the database
        statementsRepository.saveAndFlush(statements);

        int databaseSizeBeforeUpdate = statementsRepository.findAll().size();

        // Update the statements using partial update
        Statements partialUpdatedStatements = new Statements();
        partialUpdatedStatements.setId(statements.getId());

        partialUpdatedStatements.startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);

        restStatementsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStatements.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStatements))
            )
            .andExpect(status().isOk());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeUpdate);
        Statements testStatements = statementsList.get(statementsList.size() - 1);
        assertThat(testStatements.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testStatements.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingStatements() throws Exception {
        int databaseSizeBeforeUpdate = statementsRepository.findAll().size();
        statements.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatementsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, statements.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(statements))
            )
            .andExpect(status().isBadRequest());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStatements() throws Exception {
        int databaseSizeBeforeUpdate = statementsRepository.findAll().size();
        statements.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStatementsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(statements))
            )
            .andExpect(status().isBadRequest());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStatements() throws Exception {
        int databaseSizeBeforeUpdate = statementsRepository.findAll().size();
        statements.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStatementsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(statements))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Statements in the database
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStatements() throws Exception {
        // Initialize the database
        statementsRepository.saveAndFlush(statements);

        int databaseSizeBeforeDelete = statementsRepository.findAll().size();

        // Delete the statements
        restStatementsMockMvc
            .perform(delete(ENTITY_API_URL_ID, statements.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Statements> statementsList = statementsRepository.findAll();
        assertThat(statementsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
