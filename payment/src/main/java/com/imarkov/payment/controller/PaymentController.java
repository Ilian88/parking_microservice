package com.imarkov.payment.controller;

import com.imarkov.payment.model.dto.RequestPaymentDTO;
import com.imarkov.payment.model.dto.StayDetails;
import com.imarkov.payment.service.client.PaymentCalculator;
import com.imarkov.payment.service.client.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentCalculator paymentCalculator;

    public PaymentController(PaymentCalculator paymentCalculator, PaymentService paymentService) {
        this.paymentService = paymentService;
        this.paymentCalculator = paymentCalculator;
    }

    @PostMapping("/info")
    public ResponseEntity<ResponseRecord> getSum(@RequestBody @Valid StayDetails stayDetails) {
        ResponseRecord calculate = paymentCalculator.calculate(stayDetails);

        return ResponseEntity.ok().body(calculate);
    }

    @PostMapping("/request")
    public ResponseEntity<UUID> pay(@RequestBody @Valid RequestPaymentDTO paymentDTO) {
        UUID uuid = this.paymentService.executePayment(paymentDTO);

        return ResponseEntity.ok().body(uuid);
    }

    public record ResponseRecord(long timeSpentInHours, BigDecimal amountTillNow) {
    }

}
