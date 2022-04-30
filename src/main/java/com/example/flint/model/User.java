package com.example.flint.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

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
