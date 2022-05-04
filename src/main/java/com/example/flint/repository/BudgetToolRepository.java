package com.example.flint.repository;

import com.example.flint.model.BudgetTool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface BudgetToolRepository extends JpaRepository<BudgetTool, Long> {
    BudgetTool findByName(String name);
    BudgetTool findByAmountSpent (BigDecimal amountSpent);
}
