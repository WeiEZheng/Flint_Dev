package com.example.flint.model;

import com.example.flint.model.enumeration.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.Instant;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction")
public class Transaction {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date_of_transaction", nullable = false)
    private Instant dateOfTransaction;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_transaction", nullable = false)
    private TransactionType typeOfTransaction;

    @Column(name = "transaction_amount", precision = 21, scale = 2)
    private BigDecimal transactionAmount;

    @Column(name = "to_account_number")
    private Long toAccountNumber;

    @NotNull
    @Column(name = "from_account_number", nullable = false)
    private Long fromAccountNumber;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

//    @ManyToOne
//    private BankAccount bankAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Instant dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public TransactionType getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(TransactionType typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
