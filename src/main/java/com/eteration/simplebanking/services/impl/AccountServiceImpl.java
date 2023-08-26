package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.TransactionType;
import com.eteration.simplebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl {
    @Autowired
    AccountRepository accountRepository;

    public Account findAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return account;
    }

    public void updateAccount(Account account, Transaction transaction){
        if(transaction.getTransactionType().compareTo(TransactionType.DEPOSIT)==0){
            account.deposit(transaction.getAmount());
        }else if(transaction.getTransactionType().compareTo(TransactionType.WITHDRAWAL)==0){
            account.withdraw(transaction.getAmount());
        }
        accountRepository.save(account);
    }
}
