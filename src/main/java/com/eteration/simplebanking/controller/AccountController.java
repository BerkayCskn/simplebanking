package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.controller.dto.request.AccountRequest;
import com.eteration.simplebanking.controller.dto.request.BillPaymentRequest;
import com.eteration.simplebanking.controller.dto.request.TransactionRequest;
import com.eteration.simplebanking.controller.dto.response.AccountResponse;
import com.eteration.simplebanking.controller.dto.response.TransactionResultResponse;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
@Validated
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping(value = "/createAccount", consumes = "application/json")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest){
        return accountService.createAccount(accountRequest);
    }

    @GetMapping(value = "/{accountNumber}",produces = "application/json")
    public Account getAccount(@PathVariable String accountNumber) {
        return accountService.findAccount(accountNumber);
    }

    @PostMapping(value = "/credit/{accountNumber}",consumes = "application/json" )
    public ResponseEntity<TransactionResultResponse> credit(@Valid @RequestBody TransactionRequest transactionRequest,@PathVariable String accountNumber) {
        return accountService.credit(accountNumber,transactionRequest);
    }

    @PostMapping(value = "/debit/{accountNumber}",consumes = "application/json" )
    public ResponseEntity<TransactionResultResponse> debit(@Valid @RequestBody TransactionRequest transactionRequest, @PathVariable String accountNumber) {
        return accountService.debit(accountNumber,transactionRequest);
    }
    @PostMapping(value = "/billPayment/{accountNumber}",consumes = "application/json" )
    public ResponseEntity<TransactionResultResponse> billPayment(@Valid @RequestBody BillPaymentRequest billPaymentRequest, @PathVariable String accountNumber) {
        return accountService.billPayment(accountNumber,billPaymentRequest);
    }
}