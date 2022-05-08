package com.example.flint.repository;

import com.example.flint.model.BudgetTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
public interface BudgetToolRepository extends JpaRepository<BudgetTool, Long> {
    Collection <BudgetTool> findByUser(String user);
    Optional <BudgetTool> findByNameOfExpense(String nameOfExpense);
    Optional <BudgetTool> findByAmountSpent (BigDecimal amountSpent);
}
