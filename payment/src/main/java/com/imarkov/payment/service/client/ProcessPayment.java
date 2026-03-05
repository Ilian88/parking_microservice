package com.imarkov.payment.service.client;

import com.imarkov.payment.model.dto.CardDetails;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProcessPayment {
    UUID process(CardDetails cardDetails, BigDecimal amountToPay);
}
