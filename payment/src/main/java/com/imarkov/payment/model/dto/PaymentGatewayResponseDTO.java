package com.imarkov.payment.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class PaymentGatewayResponseDTO {
    private String transactionId;
    private String gatewayStatus;
    private String errorCode;
    private String errorMessage;
    private LocalDateTime receivedAt;

    @NotBlank
    public String getTransactionId() {
        return transactionId;
    }

    public PaymentGatewayResponseDTO setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getGatewayStatus() {
        return gatewayStatus;
    }

    public PaymentGatewayResponseDTO setGatewayStatus(String gatewayStatus) {
        this.gatewayStatus = gatewayStatus;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public PaymentGatewayResponseDTO setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public PaymentGatewayResponseDTO setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @PastOrPresent()
    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public PaymentGatewayResponseDTO setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
        return this;
    }
}
