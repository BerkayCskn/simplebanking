package com.eteration.simplebanking.model.transactionTypes;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Getter
@Setter
//@NoArgsConstructor
@Entity(name = "withdrawal_transaction")
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction() {
    }

    public WithdrawalTransaction(Double amount) {
        super(amount, TransactionType.WITHDRAWAL);
    }

    @Override
    protected void balanceChanges(Account account){
        account.withdraw(this.getAmount());
    }

}


