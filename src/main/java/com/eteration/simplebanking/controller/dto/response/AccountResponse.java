package com.eteration.simplebanking.controller.dto.response;

import com.eteration.simplebanking.model.Account;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


public class AccountResponse {
    private String accountNumber;
    private String owner;
    private Double balance;
    private LocalDateTime createdDate;
    private List<TransactionResponse> transactions;

    @JsonCreator
    public AccountResponse(String accountNumber, String owner, Double balance, LocalDateTime createdDate, List<TransactionResponse> transactions) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.createdDate = createdDate;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public Double getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }
}
