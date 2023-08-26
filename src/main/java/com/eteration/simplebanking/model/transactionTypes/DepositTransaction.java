package com.eteration.simplebanking.model.transactionTypes;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

// This class is a place holder you can change the complete implementation
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "deposit_transaction")
public class DepositTransaction extends Transaction {
    private String depositTransaction;

    public DepositTransaction(Double amount) {
        super(amount, TransactionType.DEPOSIT);
    }

    @Override
    protected void balanceChanges(Account account) {
        account.deposit(this.getAmount());
    }


}
