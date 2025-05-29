package com.encora.test.dto;

public class AccountRequestDTO {

    private String accountName;
    private String accountType;
    private double accountBalance;

    public AccountRequestDTO(String accountName, String accountType, double accountBalance) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
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
