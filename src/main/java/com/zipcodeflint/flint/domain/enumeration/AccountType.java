package com.zipcodeflint.flint.domain.enumeration;

/**
 * The AccountType enumeration.
 */
public enum AccountType {
    CHECKING("Checking"),
    SAVINGS("Savings");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
