package io.quarkus.bank.rest.dto;

import io.smallrye.common.constraint.NotNull;

import java.math.BigDecimal;

public class DepositRequest {

    @NotNull
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
