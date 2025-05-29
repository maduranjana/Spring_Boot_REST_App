package com.encora.test.dto;

public class AccountPageResponseDTO {
    private Long accountId;
    private String accountName;
    private String accountType;
    private double accountBalance;

    public AccountPageResponseDTO(Long accountId, String accountName, String accountType, double accountBalance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
