package com.imarkov.payment.service;

import com.imarkov.payment.model.dto.CardDetails;
import com.imarkov.payment.model.dto.RequestPaymentDTO;
import com.imarkov.payment.service.client.ProcessPayment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ProcessPaymentImpl implements ProcessPayment {
    private final RestTemplate restTemplate;

    public ProcessPaymentImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UUID process(CardDetails cardDetails, BigDecimal amountToPay) {
        ResponseEntity<UUID> responseEntity = restTemplate.postForEntity("somePath/pay", new PaymentDTOObj(cardDetails, amountToPay), UUID.class);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Unsuccesful payment");
        }

        return responseEntity.getBody();
    }

    private record PaymentDTOObj (CardDetails cardDetails, BigDecimal amountToPay) {
    }
}
