package com.finance.financemanagement.model;

public class Accounts {
    private int userId;
    private String accountType;
    private double depositAmount;

    public Accounts() {
    }

    public Accounts(int userId, String accountType, double depositAmount) {
        this.userId = userId;
        this.accountType = accountType;
        this.depositAmount = depositAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }
}
