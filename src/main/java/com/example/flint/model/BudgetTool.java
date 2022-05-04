package com.example.flint.model;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "budget_tool")
public class BudgetTool implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "original_budget", precision = 21, scale = 2)
    private BigDecimal originalBudget;

    @Column(name = "remaining_budget", precision = 21, scale = 2)
    private BigDecimal remainingBudget = originalBudget;

    @Column(name = "amount_spent", precision = 21, scale = 2)
    private BigDecimal amountSpent;

    @Column(name = "name_of_expense")
    private String nameOfExpense;

    @Column(name = "date_of_expense")
    private LocalDate dateOfExpense;

    public void setRemainingBudget(BigDecimal remainingBudget) {
        this.remainingBudget = remainingBudget.subtract(amountSpent);
    }
}
