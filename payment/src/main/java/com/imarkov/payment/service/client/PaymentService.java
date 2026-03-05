package com.imarkov.payment.service.client;

import com.imarkov.payment.model.dto.RequestPaymentDTO;

import java.util.UUID;

public interface PaymentService {
    UUID executePayment(RequestPaymentDTO paymentDTO);
}
