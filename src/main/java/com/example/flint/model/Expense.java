package com.example.flint.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="expense")
public class Expense {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal amount = new BigDecimal(0);
    private Instant date;
    private String description;

    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

}
