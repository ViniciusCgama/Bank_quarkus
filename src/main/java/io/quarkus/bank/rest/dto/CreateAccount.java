package io.quarkus.bank.rest.dto;

import java.math.BigDecimal;

public class CreateAccount {

    private Long id;
    private BigDecimal balance;
    private AccountType type;
    private Long user_id;

    public CreateAccount() {
    }

    public CreateAccount(Long userId, AccountType type, BigDecimal balance) {
        this.user_id = userId;
        this.type = type;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
