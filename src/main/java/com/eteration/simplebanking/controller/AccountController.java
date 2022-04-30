package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Request;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public Object getAccount(@PathVariable String id) {
        Account account = accountService.getAccount(id);
        return account;
    }
    @PostMapping("/credit/{id}")
    public String credit(@RequestBody Request request, @PathVariable String id ) {
        double amount = request.getAmount();
        accountService.credit(amount, id);
        return "Success";
      //  return null;
    }
    @PostMapping("/debit/{id}")
    public String debit(@RequestBody Request request, @PathVariable String id) {
        double amount = request.getAmount();
        accountService.debit(amount, id);
        return "Success";
    }
}