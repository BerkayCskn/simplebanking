package com.eteration.simplebanking.controller;

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
@RequiredArgsConstructor
@Validated
public class AccountController {
    @Autowired
    private final AccountService accountService;
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(
            @PathVariable String accountNumber
    ) {
        final Account account = accountService.findAccount(accountNumber);
        final AccountResponse accountResponse = AccountResponse.from(account);
        return ResponseEntity.ok(accountResponse);
    }

    @PostMapping("/{accountNumber}/credit")
    public ResponseEntity<TransactionResultResponse> credit(
            @PathVariable String accountNumber,
            @Valid @RequestBody TransactionRequest transactionRequest
    ) {
        final String approvalCode = accountService.credit(accountNumber, transactionRequest.amount());
        final TransactionResultResponse transactionResultResponse = new TransactionResultResponse(approvalCode, HttpStatus.OK);
        return ResponseEntity.ok(transactionResultResponse);
    }
    public Object debit() {
        return null;
	}
}