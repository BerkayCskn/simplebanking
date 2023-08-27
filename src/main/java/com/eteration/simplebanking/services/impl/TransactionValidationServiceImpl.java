package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.controller.dto.request.TransactionRequest;
import com.eteration.simplebanking.services.validation.TransactionValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionValidationServiceImpl implements TransactionValidationService {


    @Override
    public void validateTransation(TransactionRequest transactionRequest) {
        if(transactionRequest.getAmount() == null){
            throw new RuntimeException("Amount can not be empty.");
        }
    }
}
