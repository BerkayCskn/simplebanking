package com.eteration.simplebanking.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.istack.NotNull;

import javax.validation.constraints.Min;

public class TransactionRequest {
    @Min(value = 1)
    @NotNull
    private Double amount;

    @JsonCreator
    public TransactionRequest() {

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
