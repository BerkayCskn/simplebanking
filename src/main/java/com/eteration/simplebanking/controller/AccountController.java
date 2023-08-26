package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.controller.dto.request.BillPaymentRequest;
import com.eteration.simplebanking.controller.dto.request.TransactionRequest;
import com.eteration.simplebanking.controller.dto.response.AccountResponse;
import com.eteration.simplebanking.controller.dto.response.TransactionResultResponse;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
@Validated
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
  /* @GetMapping(value = "/{accountNumber}",produces = "application/json")
    public Account getAccount(
            @PathVariable String accountNumber
    ) {
        final Account account = accountService.findAccount(accountNumber);
        final AccountResponse accountResponse = AccountResponse.from(account);
        return account ;
    }
*/
    @GetMapping(value = "/{accountNumber}",produces = "application/json")
    public ResponseEntity<AccountResponse> getAccount(
            @PathVariable String accountNumber
    ) {
        final Account account = accountService.findAccount(accountNumber);
        final AccountResponse accountResponse = AccountResponse.from(account);
        return ResponseEntity.ok(accountResponse);
    }

    @PostMapping(value = "/credit/{accountNumber}",consumes = "application/json" )
    public ResponseEntity<TransactionResultResponse> credit(
            @Valid @RequestBody TransactionRequest transactionRequest,
             @PathVariable String accountNumber
    ) {
        final String approvalCode = accountService.credit(accountNumber, transactionRequest.getAmount());
        final TransactionResultResponse transactionResultResponse = new TransactionResultResponse(approvalCode, HttpStatus.OK);
        return ResponseEntity.ok(transactionResultResponse);
    }


    @PostMapping(value = "/debit/{accountNumber}",consumes = "application/json" )
    public ResponseEntity<TransactionResultResponse> debit(
            @Valid @RequestBody TransactionRequest transactionRequest,
            @PathVariable String accountNumber

    ) {
        final String approvalCode = accountService.debit(accountNumber, transactionRequest.getAmount());
        final TransactionResultResponse transactionResultResponse = new TransactionResultResponse(approvalCode, HttpStatus.OK);
        return ResponseEntity.ok(transactionResultResponse);
    }
    @PostMapping(value = "/billPayment/{accountNumber}",consumes = "application/json" )
    public ResponseEntity<TransactionResultResponse> billPayment(
            @Valid @RequestBody BillPaymentRequest billPaymentRequest,
            @PathVariable String accountNumber

    ) {
        final String approvalCode = accountService.billPayment(
                accountNumber,
                billPaymentRequest.getAmount(),
                billPaymentRequest.getPhoneNumber(),
                billPaymentRequest.getPayee()

        );
        final TransactionResultResponse transactionResultResponse = new TransactionResultResponse(approvalCode, HttpStatus.OK);
        return ResponseEntity.ok(transactionResultResponse);
    }
}