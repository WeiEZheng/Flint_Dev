package com.example.flint.repository;

import com.example.flint.model.BankAccount;
import com.example.flint.model.BudgetTool;
import org.apache.tomcat.util.http.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Modified from Jhipster

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @RestController
    @RequestMapping("/api")
    @Transactional
     class BudgetToolResource {

        private final Logger log = LoggerFactory.getLogger(BudgetToolResource.class);

        private static final String ENTITY_NAME = "budgetTool";


        private final BudgetToolRepository budgetToolRepository;

        public BudgetToolResource(BudgetToolRepository budgetToolRepository) {
            this.budgetToolRepository = budgetToolRepository;
        }


        @PostMapping("/budget-tools")
        public ResponseEntity<BudgetTool> createBudgetTool(@RequestBody BudgetTool budgetTool) throws URISyntaxException {
            log.debug("REST request to save BudgetTool : {}", budgetTool);
            if (budgetTool.getId() != null) {
                throw new Error("A new budgetTool cannot already have an ID " + ENTITY_NAME + " idexists");
            }
            BudgetTool result = budgetToolRepository.save(budgetTool);
            return ResponseEntity
                .created(new URI("/api/budget-tools/" + result.getId()))
                .body(result);
        }


        @PutMapping("/budget-tools/{id}")
        public ResponseEntity<BudgetTool> updateBudgetTool(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody BudgetTool budgetTool
        ) throws URISyntaxException {
            log.debug("REST request to update BudgetTool : {}, {}", id, budgetTool);
            if (budgetTool.getId() == null) {
                throw new Error("Invalid id "+ ENTITY_NAME + " idnull");
            }
            if (!Objects.equals(id, budgetTool.getId())) {
                throw new Error("Invalid ID " + ENTITY_NAME + " idinvalid");
            }

            if (!budgetToolRepository.existsById(id)) {
                throw new Error("Entity not found " + ENTITY_NAME + " idnotfound");
            }

            BudgetTool result = budgetToolRepository.save(budgetTool);
            return ResponseEntity
                .ok()
                .body(result);
        }



        @GetMapping("/budget-tools")
        public ResponseEntity<List<BudgetTool>> getAllBudgetTools(Pageable pageable) {
            log.debug("REST request to get a page of BudgetTools");
            Page<BudgetTool> page = budgetToolRepository.findAll(pageable);
            return ResponseEntity.ok().body(page.getContent());
        }


        @GetMapping("/budget-tools/{id}")
        public Optional<BudgetTool> getBudgetTool(@PathVariable Long id) {
            log.debug("REST request to get BudgetTool : {}", id);
            Optional<BudgetTool> budgetTool = budgetToolRepository.findById(id);
            return budgetTool;
        }


        @DeleteMapping("/budget-tools/{id}")
        public ResponseEntity<Void> deleteBudgetTool(@PathVariable Long id) {
            log.debug("REST request to delete BudgetTool : {}", id);
            budgetToolRepository.deleteById(id);
            return ResponseEntity
                .noContent()
                .build();
        }
    }

}
