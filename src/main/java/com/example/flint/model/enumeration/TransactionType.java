package com.example.flint.model.enumeration;

public enum TransactionType {
    DEBIT("Debit"),
    CREDIT("Credit"),
    TRANSFER("Transfer");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}