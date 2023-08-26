package com.eteration.simplebanking.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.istack.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class BillPaymentRequest {
    @Min(value = 1)
    @NotNull
    private Double amount;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String payee;

    @JsonCreator
    public BillPaymentRequest() {

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }
}
