package com.eteration.simplebanking.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// This class is a place holder you can change the complete implementation
@Getter
@Setter
@Entity(name = "account")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "ownerName")
    private String ownerName;
    @Column(name = "balance")
    private Double balance = 0.0;
    @Column(name = "date")
    private LocalDateTime date;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ACCOUNT_NUMBER")
    private List<Transaction> transactions;



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

    public void withdraw(double withdrawAmount) {
        this.balance = balance - withdrawAmount;
    }

    public String post(Transaction transaction) {
        final String transactionApprovalCode = transaction.executeTransactionIn(this);
        this.transactions.add(transaction);
        return transactionApprovalCode;
    }

}
