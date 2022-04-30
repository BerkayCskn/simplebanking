package com.eteration.simplebanking.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

// This class is a place holder you can change the complete implementation
public class DepositTransaction extends Transaction  {

    public DepositTransaction(double x) {
        this.amount = x;
        this.type = "DepositTransaction";
    }

    public DepositTransaction() {

    }


//    @Override
//    public void DepositTransaction(double x) {
//        this.amount = x;
//    }
}
