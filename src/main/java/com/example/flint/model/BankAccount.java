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

    public BankAccount(Long id, String accountName, BigDecimal balance, Enum accountType) {
        this.id = id;
        this.accountName = accountName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public BankAccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Enum getAccountType() {
        return accountType;
    }

    public void setAccountType(Enum accountType) {
        this.accountType = accountType;
    }
}
