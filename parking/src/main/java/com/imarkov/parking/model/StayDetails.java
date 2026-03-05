package com.imarkov.parking.model;

import org.springframework.data.util.Pair;

import java.math.BigDecimal;

public class StayDetails {
    private final long timeSpent;
    private final BigDecimal amountToPay;
    private final String currency;

    public StayDetails(Long timeSpent, BigDecimal amountToPay, String currency) {
        this.timeSpent = timeSpent;
        this.amountToPay = amountToPay;
        this.currency = currency;
    }

    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public String getCurrency() {
        return currency;
    }
}
