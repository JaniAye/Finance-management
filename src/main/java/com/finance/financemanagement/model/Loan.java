package com.finance.financemanagement.model;

public class Loan {
    private int accId;
    private double irate;
    private double loanAmo;
    private double monthlyInterest;
    private double totAmount;

    public Loan(int accId, double irate, double loanAmo, double monthlyInterest, double totAmount) {
        this.accId = accId;
        this.irate = irate;
        this.loanAmo = loanAmo;
        this.monthlyInterest = monthlyInterest;
        this.totAmount = totAmount;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public double getIrate() {
        return irate;
    }

    public void setIrate(double irate) {
        this.irate = irate;
    }

    public double getLoanAmo() {
        return loanAmo;
    }

    public void setLoanAmo(double loanAmo) {
        this.loanAmo = loanAmo;
    }

    public double getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(double monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public double getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(double totAmount) {
        this.totAmount = totAmount;
    }

    public Loan() {
    }


}
