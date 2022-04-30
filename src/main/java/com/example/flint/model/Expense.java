package com.example.flint.model;

import lombok.*;

import javax.persistence.*;
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
    private Instant date;
    private String description;

    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

}
