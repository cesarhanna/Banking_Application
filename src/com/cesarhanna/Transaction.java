package com.cesarhanna;

import java.util.Date;

public class Transaction {

    private Double amount;
    private final Date date;

    public Transaction(Double amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
