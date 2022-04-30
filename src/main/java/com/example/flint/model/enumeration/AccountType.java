package com.example.flint.model.enumeration;

public enum AccountType {
    SAVINGS("Savings"),
    CHECKING("Checking");


    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

}

