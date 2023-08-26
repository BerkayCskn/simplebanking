package com.eteration.simplebanking.model.transactionTypes;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;

public class BillPaymentTransaction extends Transaction {
    @Override
    protected void balanceChanges(Account account) {

    }
}
