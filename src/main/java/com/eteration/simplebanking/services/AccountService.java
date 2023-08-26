package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;

// This class is a place holder you can change the complete implementation
public interface AccountService {
    public Account findAccount(String accountId);
    String debit(String accountNumber, Double amount);
    String credit(String accountNumber, Double amount);
}
