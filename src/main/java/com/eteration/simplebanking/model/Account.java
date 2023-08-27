package com.eteration.simplebanking.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// This class is a place holder you can change the complete implementation
@Getter
@Setter
@Entity(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "accountNumber")
    private String accountNumber;

    @NotNull
    @Column(name = "ownerName")
    private String ownerName;

    @Column(name = "balance")
    private Double balance = 0.0;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions = new ArrayList<>();
    public Account() {

    }


    public Account(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
    }

    @PrePersist
    private void onPersist() {
        this.date = LocalDateTime.now();
    }

    public void deposit(double depositAmount) {
        this.balance = balance + depositAmount;
    }

    public void withdraw(double withdrawAmount){
        this.balance = balance - withdrawAmount;
    }

    public String post(Transaction transaction) {
        final String transactionApprovalCode = transaction.executeTransactionIn(this);
        this.transactions.add(transaction);
        return transactionApprovalCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
