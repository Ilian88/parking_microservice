package com.imarkov.payment.model.dto;

import com.imarkov.payment.enums.Currency;
import com.imarkov.payment.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PaymentDTO {
    private Long userId;
    private Long sessionId;
    private PaymentStatus status;
    private BigDecimal amount;
    private Currency currency;
    private PaymentMethodDetailsDTO paymentMethodDetailsDTO;

    @NotNull
    public Long getUserId() {
        return userId;
    }

    public PaymentDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @NotNull
    public Long getSessionId() {
        return sessionId;
    }

    public PaymentDTO setSessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    @NotBlank
    public PaymentStatus getStatus() {
        return status;
    }

    public PaymentDTO setStatus(PaymentStatus status) {
        this.status = status;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @NotBlank
    public Currency getCurrency() {
        return currency;
    }

    public PaymentDTO setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public PaymentMethodDetailsDTO getPaymentMethodDetailsDTO() {
        return paymentMethodDetailsDTO;
    }

    public PaymentDTO setPaymentMethodDetailsDTO(PaymentMethodDetailsDTO paymentMethodDetailsDTO) {
        this.paymentMethodDetailsDTO = paymentMethodDetailsDTO;
        return this;
    }
}
