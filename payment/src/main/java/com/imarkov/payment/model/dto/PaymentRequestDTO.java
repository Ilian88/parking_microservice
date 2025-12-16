package com.imarkov.payment.model.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class PaymentRequestDTO {
    private LocalDateTime enteredAt;
    private LocalDateTime leftAt;
    private double ratePerHour;


    public PaymentRequestDTO() {
    }

    @NotNull(message = "EnteredAt property is mandatory")
    public LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    public PaymentRequestDTO setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
        return this;
    }

    @NotNull(message = "LeftAt property is mandatory")
    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public PaymentRequestDTO setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
        return this;
    }

    @NotNull(message = "Rate per hour is mandatory")
    public double getRatePerHour() {
        return ratePerHour;
    }

    public PaymentRequestDTO setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
        return this;
    }
}
