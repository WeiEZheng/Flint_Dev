package com.example.flint.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Entity
@Data
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToMany
    private Set<Transaction> transaction;
}
