package com.zipcodeflint.flint.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zipcodeflint.flint.domain.enumeration.TransactionType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Transactions.
 */
@Entity
@Table(name = "transactions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Transactions implements Serializable {

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

    @DecimalMin(value = "0")
    @Column(name = "transaction_amount", precision = 21, scale = 2)
    private BigDecimal transactionAmount;

    @Column(name = "to_account_number")
    private String toAccountNumber;

    @NotNull
    @Column(name = "from_account_number", nullable = false)
    private String fromAccountNumber;

    @ManyToOne
    @JsonIgnoreProperties(value = { "bankAccount", "transactions" }, allowSetters = true)
    private Statements statements;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Transactions id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateOfTransaction() {
        return this.dateOfTransaction;
    }

    public Transactions dateOfTransaction(Instant dateOfTransaction) {
        this.setDateOfTransaction(dateOfTransaction);
        return this;
    }

    public void setDateOfTransaction(Instant dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public TransactionType getTypeOfTransaction() {
        return this.typeOfTransaction;
    }

    public Transactions typeOfTransaction(TransactionType typeOfTransaction) {
        this.setTypeOfTransaction(typeOfTransaction);
        return this;
    }

    public void setTypeOfTransaction(TransactionType typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public BigDecimal getTransactionAmount() {
        return this.transactionAmount;
    }

    public Transactions transactionAmount(BigDecimal transactionAmount) {
        this.setTransactionAmount(transactionAmount);
        return this;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getToAccountNumber() {
        return this.toAccountNumber;
    }

    public Transactions toAccountNumber(String toAccountNumber) {
        this.setToAccountNumber(toAccountNumber);
        return this;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getFromAccountNumber() {
        return this.fromAccountNumber;
    }

    public Transactions fromAccountNumber(String fromAccountNumber) {
        this.setFromAccountNumber(fromAccountNumber);
        return this;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Statements getStatements() {
        return this.statements;
    }

    public void setStatements(Statements statements) {
        this.statements = statements;
    }

    public Transactions statements(Statements statements) {
        this.setStatements(statements);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transactions)) {
            return false;
        }
        return id != null && id.equals(((Transactions) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transactions{" +
            "id=" + getId() +
            ", dateOfTransaction='" + getDateOfTransaction() + "'" +
            ", typeOfTransaction='" + getTypeOfTransaction() + "'" +
            ", transactionAmount=" + getTransactionAmount() +
            ", toAccountNumber='" + getToAccountNumber() + "'" +
            ", fromAccountNumber='" + getFromAccountNumber() + "'" +
            "}";
    }
}
