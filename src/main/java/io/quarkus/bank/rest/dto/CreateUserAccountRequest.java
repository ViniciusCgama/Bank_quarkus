package io.quarkus.bank.rest.dto;

import java.math.BigDecimal;

public class CreateUserAccountRequest {

    private Long accountId;
    private String name;
    private Integer age;
    private String tell;
    private String adress;
    private Long userId;
    private AccountType type;
    private BigDecimal balance;

    public CreateUserAccountRequest() {
    }

    public CreateUserAccountRequest(String name, Integer age, String tell, String adress, Long accountId, AccountType type, BigDecimal balance) {
        this.name = name;
        this.age = age;
        this.tell = tell;
        this.adress = adress;
        this.accountId = accountId;
        this.type = type;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Long getUser_Id() {
        return userId;
    }

    public void setUser_Id(Long user_Id) {
        this.userId = user_Id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

