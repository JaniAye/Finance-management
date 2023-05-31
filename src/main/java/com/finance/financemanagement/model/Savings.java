package com.finance.financemanagement.model;

public class Savings {
    private String account_id;
  private double interest_rate;
   private double balance;

    public Savings() {
    }

    public Savings(String account_id, double interest_rate, double balance) {
        this.account_id = account_id;
        this.interest_rate = interest_rate;
        this.balance = balance;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
