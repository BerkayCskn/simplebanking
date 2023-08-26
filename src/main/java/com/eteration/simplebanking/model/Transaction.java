package com.eteration.simplebanking.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

// This class is a place holder you can change the complete implementation
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "transaction")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "amount")
    private Double amount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "approval_code")
    private String approvalCode;

    public Transaction(Double amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
    }

    protected String executeTransactionIn(Account account) {
        this.balanceChanges(account);
        this.approvalCode = UUID.randomUUID().toString();
        return approvalCode;
    }
    @PrePersist
    private void onPersist() {
        this.date = LocalDateTime.now();
    }

    protected abstract void balanceChanges(Account account);

}
