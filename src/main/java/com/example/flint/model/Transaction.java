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
    private String toAccountNumber;

    @NotNull
    @Column(name = "from_account_number", nullable = false)
    private String fromAccountNumber;

//    @ManyToOne
//    @JsonIgnoreProperties(value = { "bankAccount", "transactions" }, allowSetters = true)
//    private Statements statements;
}
