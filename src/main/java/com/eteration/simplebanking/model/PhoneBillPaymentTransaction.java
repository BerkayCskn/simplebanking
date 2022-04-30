package com.eteration.simplebanking.model;

public class PhoneBillPaymentTransaction extends Transaction{

    String operator;

    String phoneNumber;

    public PhoneBillPaymentTransaction(String operator, String phoneNumber, double x) {
        this.operator = operator;
        this.phoneNumber = phoneNumber;
        this.amount = x;
        this.type = "PhoneBillPaymentTransaction";
    }

    public PhoneBillPaymentTransaction() {

    }
}
