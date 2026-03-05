package com.imarkov.payment.service;

import com.imarkov.payment.controller.PaymentController;
import com.imarkov.payment.model.dto.RequestPaymentDTO;
import com.imarkov.payment.service.client.PaymentCalculator;
import com.imarkov.payment.service.client.PaymentService;
import com.imarkov.payment.service.client.ProcessPayment;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentCalculator paymentCalculator;
    private final ProcessPayment processPayment;

    public PaymentServiceImpl(PaymentCalculator paymentCalculator, ProcessPayment processPayment) {
        this.paymentCalculator = paymentCalculator;
        this.processPayment = processPayment;
    }


    @Override
    public UUID executePayment(RequestPaymentDTO paymentDTO) {
        PaymentController.ResponseRecord calculate = paymentCalculator.calculate(paymentDTO.stayDetails());

        return processPayment.process(paymentDTO.cardDetails(), calculate.amountTillNow());
    }
}
