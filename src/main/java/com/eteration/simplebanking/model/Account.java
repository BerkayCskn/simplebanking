package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// This class is a place holder you can change the complete implementation
@Entity
@Table(name = "account")
@EnableAutoConfiguration
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    String owner;
    @Column
    double balance;
    @Column
    String accountNumber;

    @OneToMany(targetEntity = Transaction.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    List<Transaction> transactions = new ArrayList<Transaction>();


    public void setId(Long id) {
        this.id = id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0;
        List<Transaction> transactions = new ArrayList<Transaction>();
        this.setTransactions(transactions);
    }

    public Account() {

    }


    public double credit(double x) {
        return balance += x;
    }

    public double debit(double x) {
        return balance -= x;
    }

    public void post(Transaction transaction) {
        transactions.add(transaction);
        if (transaction.type == "DepositTransaction") {
            this.credit(transaction.amount);
        } else if (transaction.type == "WithdrawalTransaction") {
            this.debit(transaction.amount);
        } else if(transaction.type == "PhoneBillPaymentTransaction"){
            this.debit(transaction.amount);
        }
    }

}
