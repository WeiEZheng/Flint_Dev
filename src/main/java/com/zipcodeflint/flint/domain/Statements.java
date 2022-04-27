package com.zipcodeflint.flint.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Statements.
 */
@Entity
@Table(name = "statements")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Statements implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "statements" }, allowSetters = true)
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "statements")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "statements" }, allowSetters = true)
    private Set<Transactions> transactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Statements id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public Statements startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public Statements endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Statements bankAccount(BankAccount bankAccount) {
        this.setBankAccount(bankAccount);
        return this;
    }

    public Set<Transactions> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        if (this.transactions != null) {
            this.transactions.forEach(i -> i.setStatements(null));
        }
        if (transactions != null) {
            transactions.forEach(i -> i.setStatements(this));
        }
        this.transactions = transactions;
    }

    public Statements transactions(Set<Transactions> transactions) {
        this.setTransactions(transactions);
        return this;
    }

    public Statements addTransactions(Transactions transactions) {
        this.transactions.add(transactions);
        transactions.setStatements(this);
        return this;
    }

    public Statements removeTransactions(Transactions transactions) {
        this.transactions.remove(transactions);
        transactions.setStatements(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Statements)) {
            return false;
        }
        return id != null && id.equals(((Statements) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Statements{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
