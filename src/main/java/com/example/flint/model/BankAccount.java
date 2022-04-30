package com.example.flint.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bankAccount")
public class BankAccount {
    @Id
    @GeneratedValue
    Long id;
}
