package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.TransactionType;
import com.eteration.simplebanking.model.transactionTypes.DepositTransaction;
import com.eteration.simplebanking.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl {

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


    @Override
    public String credit(String accountNumber, Double amount) {
        return this.post(accountNumber, new DepositTransaction(amount));
    }

    private String post(String accountNumber, Transaction transaction) {
        final Account account = this.getBankAccount(accountNumber);
        final String transactionApprovalCode = account.post(transaction);
        accountRepository.update(bankAccount);
        return transactionApprovalCode;
    }

    private Account getBankAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);}
}
