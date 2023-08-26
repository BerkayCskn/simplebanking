package com.eteration.simplebanking.controller.dto.response;

import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.TransactionType;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionResponse {
    private Double amount;
    private TransactionType type;
    private String approvalCode;
    private LocalDateTime date;

    @JsonCreator
    public TransactionResponse(Double amount,
                               TransactionType type,
                               String approvalCode,
                               LocalDateTime createdDate) {
        this.amount = amount;
        this.type = type;
        this.approvalCode = approvalCode;
        this.date = createdDate;
    }
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getApprovalCode(),
                transaction.getDate()
        );
    }

    public static List<TransactionResponse> from(List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionResponse::from)
                .collect(Collectors.toList());
    }
}
