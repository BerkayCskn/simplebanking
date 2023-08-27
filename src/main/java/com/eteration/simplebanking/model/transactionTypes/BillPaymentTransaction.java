package com.eteration.simplebanking.model.transactionTypes;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "billpayment_transaction")
public class BillPaymentTransaction extends Transaction  {

    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "PAYEE")
    private String payee;

    public BillPaymentTransaction(Double amount, String phoneNumber, String payee) {
        super(amount, TransactionType.BILL_PAYMENT);
        this.phoneNumber = phoneNumber;
        this.payee = payee;
    }

    public BillPaymentTransaction() {

    }

    @Override
    protected void balanceChanges(Account account) {
        account.withdraw(this.getAmount());
    }
}
