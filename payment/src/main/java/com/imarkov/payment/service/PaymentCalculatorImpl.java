package com.imarkov.payment.service;

import com.imarkov.payment.controller.PaymentController;
import com.imarkov.payment.model.dto.PaymentRequestDTO;
import com.imarkov.payment.service.client.PaymentCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class PaymentCalculatorImpl implements PaymentCalculator {

    @Override
    public PaymentController.ResponseRecord calculate(PaymentRequestDTO paymentRequestDTO) {
        long between = ChronoUnit.HOURS.between(paymentRequestDTO.getEnteredAt(), paymentRequestDTO.getLeftAt());
        BigDecimal amount = calculateEng(
                between,
                paymentRequestDTO.getRatePerHour());

        return new PaymentController.ResponseRecord(between, amount);
    }

    private BigDecimal calculateEng(long durationInHours, double ratePerHour) {
        return BigDecimal.valueOf((durationInHours + 1) * ratePerHour);
    }
}
