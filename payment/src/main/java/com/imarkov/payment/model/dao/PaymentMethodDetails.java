package com.imarkov.payment.model.dao;

import com.imarkov.payment.enums.PaymentMethodType;
import jakarta.persistence.*;

@Entity
public class PaymentMethodDetails extends BaseEntity {
    private PaymentMethodType methodType;
    private String maskedCardNumber;  // last 4 digits
    private String provider; // e.g., "VISA", "PayPal"
    private Payment payment;

    @Enumerated(EnumType.STRING)
    public PaymentMethodType getMethodType() {
        return methodType;
    }

    public PaymentMethodDetails setMethodType(PaymentMethodType methodType) {
        this.methodType = methodType;
        return this;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    public PaymentMethodDetails setMaskedCardNumber(String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
        return this;
    }

    public String getProvider() {
        return provider;
    }

    public PaymentMethodDetails setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    @OneToOne
    @JoinColumn(name = "payment_id")
    public Payment getPayment() {
        return payment;
    }

    public PaymentMethodDetails setPayment(Payment payment) {
        this.payment = payment;
        return this;
    }
}
