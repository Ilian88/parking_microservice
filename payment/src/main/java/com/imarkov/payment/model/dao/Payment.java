package com.imarkov.payment.model.dao;

import com.imarkov.payment.enums.Currency;
import com.imarkov.payment.enums.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment extends BaseEntity {
    private Long userId;
    private Long sessionId;
    private PaymentStatus status;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Currency currency = Currency.BGN;
    private PaymentMethodDetails methodDetails;
    private PaymentGatewayResponse gatewayResponse;

    @Column(nullable = false)
    public Long getUserId() {
        return userId;
    }

    public Payment setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Column(nullable = false)
    public Long getSessionId() {
        return sessionId;
    }

    public Payment setSessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Payment setStatus(PaymentStatus status) {
        this.status = status;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Payment setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public Currency getCurrency() {
        return currency;
    }

    public Payment setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Payment setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Payment setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    public PaymentMethodDetails getMethodDetails() {
        return methodDetails;
    }

    public Payment setMethodDetails(PaymentMethodDetails methodDetails) {
        this.methodDetails = methodDetails;
        return this;
    }

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    public PaymentGatewayResponse getGatewayResponse() {
        return gatewayResponse;
    }

    public Payment setGatewayResponse(PaymentGatewayResponse gatewayResponse) {
        this.gatewayResponse = gatewayResponse;
        return this;
    }
}
