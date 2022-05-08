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

    @Column(name = "user")
    private String user;

    @Column(name = "amount_spent", precision = 21, scale = 2)
    private BigDecimal amountSpent;

    @Column(name = "name_of_expense")
    private String nameOfExpense;

    @Column(name = "date_of_expense")
    private LocalDate dateOfExpense;

    @ManyToOne
    private Category category;

    @Override
    public String toString() {
        return "BudgetTool{" +
            "id=" + id +
            ", user=" + user +
            ", amountSpent=" + amountSpent +
            ", nameOfExpense='" + nameOfExpense + '\'' +
            ", dateOfExpense=" + dateOfExpense +
            ", category=" + category +
            '}';
    }
}
