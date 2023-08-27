package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.controller.dto.request.AccountRequest;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repositories.AccountRepository;
import com.eteration.simplebanking.services.validation.AccountValidationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountValidationServiceImpl implements AccountValidationService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void validateAccount(AccountRequest accountRequest) {
        if(accountRequest.getAccountNumber() == null){
            throw new RuntimeException("Account number not found.");
        }
        Account account = accountRepository.findByAccountNumber(accountRequest.getAccountNumber());
        if(account != null){
            throw new RuntimeException("Account number already exist.");
        }
    }

    @Override
    public void validateBalance(Double amount,String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Account balance.");
        }
    }
}
