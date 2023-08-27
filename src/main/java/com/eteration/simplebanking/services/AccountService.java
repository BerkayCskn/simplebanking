package com.eteration.simplebanking.services;


import com.eteration.simplebanking.controller.dto.request.AccountRequest;
import com.eteration.simplebanking.controller.dto.request.BillPaymentRequest;
import com.eteration.simplebanking.controller.dto.request.TransactionRequest;
import com.eteration.simplebanking.controller.dto.response.AccountResponse;
import com.eteration.simplebanking.controller.dto.response.TransactionResultResponse;
import com.eteration.simplebanking.model.Account;
import org.springframework.http.ResponseEntity;

// This class is a place holder you can change the complete implementation
public interface AccountService {
    ResponseEntity<AccountResponse> createAccount(AccountRequest accountRequest);
    Account findAccount(String accountNumber);
    ResponseEntity<TransactionResultResponse> debit(String accountNumber, TransactionRequest transactionRequest);
    ResponseEntity<TransactionResultResponse> credit(String accountNumber, TransactionRequest transactionRequest);
    ResponseEntity<TransactionResultResponse> billPayment(String accountNumber, BillPaymentRequest billPaymentRequest);
}
