package com.eteration.simplebanking.controller.dto.response;

import com.eteration.simplebanking.model.Account;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.util.List;

public class AccountResponse {
    private String accountNumber;
    private String owner;
    private Double balance;
    private LocalDateTime date;
    private List<TransactionResponse> transactions;

    @JsonCreator
    public AccountResponse(String accountNumber,
                               String owner,
                               Double balance,
                               LocalDateTime date,
                               List<TransactionResponse> transactions) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.date = date;
        this.transactions = transactions;
    }
    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getAccountNumber(),
                account.getOwnerName(),
                account.getBalance(),
                account.getDate(),
                TransactionResponse.from(account.getTransactions())
        );
    }
}
