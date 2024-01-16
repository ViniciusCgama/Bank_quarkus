package io.quarkus.bank.rest.dto;

public class CreateUserRequest {

    private String name;
    private Integer age;
    private String tell;
    private String adress;
    private AccountType type;

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getTell() {
        return tell;
    }

    public String getAdress() {
        return adress;
    }
}
