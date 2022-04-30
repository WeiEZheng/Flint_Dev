package com.example.flint.model;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
}
