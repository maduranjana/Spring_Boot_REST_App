package com.encora.test.dto;

public class AccountResponseDTO {
    private Long accountID;
    private String message;

    public AccountResponseDTO(Long accountID, String message) {
        this.accountID = accountID;
        this.message = message;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
