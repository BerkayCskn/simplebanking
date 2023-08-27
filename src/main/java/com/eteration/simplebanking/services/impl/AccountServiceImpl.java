package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.controller.dto.request.AccountRequest;
import com.eteration.simplebanking.controller.dto.request.BillPaymentRequest;
import com.eteration.simplebanking.controller.dto.request.TransactionRequest;
import com.eteration.simplebanking.controller.dto.response.AccountResponse;
import com.eteration.simplebanking.controller.dto.response.TransactionResultResponse;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.transactionTypes.BillPaymentTransaction;
import com.eteration.simplebanking.model.transactionTypes.DepositTransaction;
import com.eteration.simplebanking.model.transactionTypes.WithdrawalTransaction;
import com.eteration.simplebanking.repositories.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.services.validation.AccountValidationService;
import com.eteration.simplebanking.services.validation.TransactionValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountValidationService accountValidationService;
    @Autowired
    TransactionValidationService transactionValidationService;

    @Override
    public ResponseEntity<AccountResponse> createAccount(AccountRequest accountRequest) {
        accountValidationService.validateAccount(accountRequest);
        Account account = new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setOwnerName(accountRequest.getOwnerName());
        accountRepository.save(account);
        AccountResponse accountResponse = new AccountResponse(account.getAccountNumber(),account.getOwnerName(),account.getBalance(),account.getDate(),account.getTransactions());
        return ResponseEntity.ok(accountResponse);
    }

    @Override
    public Account findAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return account;
    }

    @Override
    public ResponseEntity<TransactionResultResponse> billPayment(String accountNumber, BillPaymentRequest billPaymentRequest) {
        String approvalCode = this.post(accountNumber,new BillPaymentTransaction(billPaymentRequest.getAmount(),billPaymentRequest.getPhoneNumber(),billPaymentRequest.getPayee()));
        final TransactionResultResponse transactionResultResponse = new TransactionResultResponse(approvalCode, HttpStatus.OK);
        return ResponseEntity.ok(transactionResultResponse);
    }

    @Override
    public ResponseEntity<TransactionResultResponse> credit(String accountNumber, TransactionRequest transactionRequest) {
        transactionValidationService.validateTransation(transactionRequest);
        String approvalCode = this.post(accountNumber, new DepositTransaction(transactionRequest.getAmount()));
        final TransactionResultResponse transactionResultResponse = new TransactionResultResponse(approvalCode, HttpStatus.OK);
        return ResponseEntity.ok(transactionResultResponse);

    }

    @Override
    public ResponseEntity<TransactionResultResponse> debit(String accountNumber, TransactionRequest transactionRequest)  {
        accountValidationService.validateBalance(transactionRequest.getAmount(),accountNumber);
        transactionValidationService.validateTransation(transactionRequest);
        String approvalCode = this.post(accountNumber, new WithdrawalTransaction(transactionRequest.getAmount()));
        final TransactionResultResponse transactionResultResponse = new TransactionResultResponse(approvalCode, HttpStatus.OK);
        return ResponseEntity.ok(transactionResultResponse);
    }

    private String post(String accountNumber, Transaction transaction) {
        final Account account = this.getBankAccount(accountNumber);
        final String transactionApprovalCode = account.post(transaction);
        accountRepository.save(account);
        return transactionApprovalCode;
    }

    private Account getBankAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);}
}
