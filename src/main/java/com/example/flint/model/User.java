package com.example.flint.model;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
}
