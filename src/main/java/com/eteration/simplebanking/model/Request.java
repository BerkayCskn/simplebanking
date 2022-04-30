package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


public class Request {
    @JsonProperty
    double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
