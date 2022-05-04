package com.example.flint.controller;

import com.example.flint.model.BudgetTool;
import com.example.flint.repository.BudgetToolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api")

public class BudgetToolController {

        private BudgetToolRepository budgetRepository;

        public BudgetToolController(BudgetToolRepository budgetRepository){
            super();
            this.budgetRepository = budgetRepository;
        }

        @GetMapping("/budget_tool")
        Collection<BudgetTool> budgetItems(){
            log.info("Getting all Budget Items");
            return budgetRepository.findAll();
        }
        @GetMapping("/budget_tool/{id}")
        ResponseEntity<?> getBudgetItem(@PathVariable Long id){
            log.info("Getting category item by {}", id);

            Optional<BudgetTool> category = budgetRepository.findById(id);
            return category.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        @PostMapping("/budget_tool")
        ResponseEntity<BudgetTool> createCategory(@Valid @RequestBody BudgetTool budgetItem) throws URISyntaxException {
            BudgetTool result=budgetRepository.save(budgetItem);
            return ResponseEntity.created(new URI("/api/budget_tool" + result.getId())).body(result);
        }

        @PutMapping("/budget_tool/{id}")
        ResponseEntity <BudgetTool> updateCategory(@Valid @RequestBody BudgetTool budgetItem) throws URISyntaxException {
            BudgetTool result=budgetRepository.save(budgetItem);
            return ResponseEntity.ok().body(result);
        }

        @DeleteMapping("/budget_tool/{id}")
        ResponseEntity<?> deleteCategory(@PathVariable Long id){
            budgetRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
}
