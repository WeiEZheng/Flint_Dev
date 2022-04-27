package com.zipcodeflint.flint.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zipcodeflint.flint.domain.enumeration.AccountType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BankAccount.
 */
@Entity
@Table(name = "bank_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @NotNull
    @Column(name = "account_name", nullable = false)
    private String accountName;

    @NotNull
    @Column(name = "balance", precision = 21, scale = 2, nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "bankAccount")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "bankAccount", "transactions" }, allowSetters = true)
    private Set<Statements> statements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BankAccount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public BankAccount accountNumber(String accountNumber) {
        this.setAccountNumber(accountNumber);
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public BankAccount accountName(String accountName) {
        this.setAccountName(accountName);
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public BankAccount balance(BigDecimal balance) {
        this.setBalance(balance);
        return this;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public BankAccount accountType(AccountType accountType) {
        this.setAccountType(accountType);
        return this;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BankAccount user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<Statements> getStatements() {
        return this.statements;
    }

    public void setStatements(Set<Statements> statements) {
        if (this.statements != null) {
            this.statements.forEach(i -> i.setBankAccount(null));
        }
        if (statements != null) {
            statements.forEach(i -> i.setBankAccount(this));
        }
        this.statements = statements;
    }

    public BankAccount statements(Set<Statements> statements) {
        this.setStatements(statements);
        return this;
    }

    public BankAccount addStatements(Statements statements) {
        this.statements.add(statements);
        statements.setBankAccount(this);
        return this;
    }

    public BankAccount removeStatements(Statements statements) {
        this.statements.remove(statements);
        statements.setBankAccount(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        return id != null && id.equals(((BankAccount) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankAccount{" +
            "id=" + getId() +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", balance=" + getBalance() +
            ", accountType='" + getAccountType() + "'" +
            "}";
    }
}
