package com.imarkov.payment.model.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class StayDetails {
    private LocalDateTime enteredAt;
    private LocalDateTime leftAt;
    private double ratePerHour;

    public StayDetails() {}

    @NotNull(message = "EnteredAt property is mandatory")
    public LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    public StayDetails setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
        return this;
    }

    @NotNull(message = "LeftAt property is mandatory")
    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public StayDetails setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
        return this;
    }

    @NotNull(message = "Rate per hour is mandatory")
    public double getRatePerHour() {
        return ratePerHour;
    }

    public StayDetails setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
        return this;
    }
}
