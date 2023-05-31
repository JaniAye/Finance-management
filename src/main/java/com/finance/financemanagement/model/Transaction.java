package com.finance.financemanagement.model;

import java.util.Date;

public class Transaction {
    private int accId;
    private String tType;
    private Double amount;
    private Date date;
    private String remark;

    public Transaction() {
    }

    public Transaction(int accId, String tType, Double amount, Date date, String remark) {
        this.accId = accId;
        this.tType = tType;
        this.amount = amount;
        this.date = date;
        this.remark = remark;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
