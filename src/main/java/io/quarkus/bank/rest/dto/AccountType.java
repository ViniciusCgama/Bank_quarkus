package io.quarkus.bank.rest.dto;

public enum AccountType {
    CURRENT(0),
    SAVINGS(1);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public static boolean isValid(AccountType type) {
        return true;
    }

    public int getValue() {
        return value;
    }
}