package com.imarkov.payment.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

@Entity
public class PaymentGatewayResponse extends BaseEntity {
    private String transactionId;
    private String gatewayStatus;
    private String errorCode;
    private String errorMessage;
    private Payment payment;
    private LocalDateTime receivedAt;

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentGatewayResponse setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }


    public String getGatewayStatus() {
        return gatewayStatus;
    }

    public PaymentGatewayResponse setGatewayStatus(String gatewayStatus) {
        this.gatewayStatus = gatewayStatus;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public PaymentGatewayResponse setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public PaymentGatewayResponse setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @OneToOne
    @JoinColumn(name = "payment_id")
    public Payment getPayment() {
        return payment;
    }

    public PaymentGatewayResponse setPayment(Payment payment) {
        this.payment = payment;
        return this;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public PaymentGatewayResponse setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
        return this;
    }
}
