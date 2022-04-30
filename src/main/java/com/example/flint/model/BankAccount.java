package com.example.flint.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="bankAccount")
public class BankAccount {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column(name="accountName")
    String accountName = "";

    @Column(name="balance")
    BigDecimal balance;

    @Column(name="accountType")
    Enum accountType;
}
