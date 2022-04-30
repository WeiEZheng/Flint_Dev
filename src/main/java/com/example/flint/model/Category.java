package com.example.flint.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private User user;
}
