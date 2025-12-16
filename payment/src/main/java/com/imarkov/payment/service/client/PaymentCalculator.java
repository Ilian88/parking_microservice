package com.imarkov.payment.service.client;

import com.imarkov.payment.controller.PaymentController;
import com.imarkov.payment.model.dto.PaymentRequestDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PaymentCalculator {
    PaymentController.ResponseRecord calculate(PaymentRequestDTO paymentRequestDTO);
}
