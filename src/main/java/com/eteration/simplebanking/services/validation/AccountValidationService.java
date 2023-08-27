package com.eteration.simplebanking.services.validation;

import com.eteration.simplebanking.controller.dto.request.AccountRequest;

public interface AccountValidationService {
    void validateAccount(AccountRequest accountRequest);
    void validateBalance(Double amount, String accountNumber);
}
