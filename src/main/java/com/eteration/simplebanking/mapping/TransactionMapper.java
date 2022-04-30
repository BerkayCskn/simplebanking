package com.eteration.simplebanking.mapping;

import com.eteration.simplebanking.model.GetTransactionsResponse;
import com.eteration.simplebanking.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapper {

    public List<Transaction> map(List<GetTransactionsResponse> transactionList) {
        List<Transaction> transactions = new ArrayList<Transaction>();

        for (GetTransactionsResponse transaction : transactionList) {
            Transaction newTransaction = new Transaction(){};
            newTransaction.setDate(transaction.getDate());
            newTransaction.setType(transaction.getType());
            newTransaction.setAmount(transaction.getAmount());
            transactions.add(newTransaction);
        }
        return transactions;
    }

}
