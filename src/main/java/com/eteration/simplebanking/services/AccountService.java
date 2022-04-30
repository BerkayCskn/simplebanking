package com.eteration.simplebanking.services;


import com.eteration.simplebanking.mapping.TransactionMapper;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.GetTransactionsResponse;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// This class is a place holder you can change the complete implementation
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionMapper transactionMapper;

    public void credit(double x, String accountNumber) {
        Account account = new Account();
        account = accountRepository.findByAccountNumber(accountNumber);
        account.credit(x);
        accountRepository.save(account);
    }

    public void debit(double x, String accountNumber) {
        Account account = new Account();
        account = accountRepository.findByAccountNumber(accountNumber);
        account.debit(x);
        accountRepository.save(account);
    }


    public Account getAccount(String accountNumber) {
        Account account = new Account();
        account = accountRepository.findByAccountNumber(accountNumber);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions = transactionMapper.map(transactionRepository.getTransactions(account.getId()));
        //transactions = transactionMapper.map(transactionRepository.findAll());
        account.setTransactions(transactions);
        return account;

    }


}
