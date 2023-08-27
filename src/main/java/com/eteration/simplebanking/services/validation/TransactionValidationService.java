package com.eteration.simplebanking.services.validation;

import com.eteration.simplebanking.controller.dto.request.TransactionRequest;

public interface TransactionValidationService {
    void validateTransation(TransactionRequest transactionRequest);
}
