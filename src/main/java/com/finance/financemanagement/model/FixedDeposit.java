package com.finance.financemanagement.model;

import java.util.Date;

public class FixedDeposit {

    private String fdAcc;

    private Date createDate;
    private Date mDate;
    private double depositAmo;
    private double interestEarned;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public FixedDeposit(String fdAcc, Date createDate,Date mDate, double depositAmo, double interestEarned) {
        this.fdAcc = fdAcc;
        this.createDate = createDate;
        this.mDate = mDate;
        this.depositAmo = depositAmo;
        this.interestEarned = interestEarned;
    }

    public FixedDeposit() {
    }

    public String getFdAcc() {
        return fdAcc;
    }

    public void setFdAcc(String fdAcc) {
        this.fdAcc = fdAcc;
    }
    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public double getDepositAmo() {
        return depositAmo;
    }

    public void setDepositAmo(double depositAmo) {
        this.depositAmo = depositAmo;
    }

    public double getInterestEarned() {
        return interestEarned;
    }

    public void setInterestEarned(double interestEarned) {
        this.interestEarned = interestEarned;
    }
}
