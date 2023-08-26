package com.eteration.simplebanking.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;

public class TransactionResultResponse {
    private String approvalCode;
    private HttpStatus status;

    @JsonCreator
    public TransactionResultResponse(String approvalCode, HttpStatus status) {
        this.approvalCode = approvalCode;
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
