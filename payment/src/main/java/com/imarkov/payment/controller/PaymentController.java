package com.imarkov.payment.controller;

import com.imarkov.payment.model.dto.PaymentRequestDTO;
import com.imarkov.payment.service.client.PaymentCalculator;
import com.imarkov.payment.service.client.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payment")
public class PaymentController {
//    private final PaymentService paymentService;
    private final PaymentCalculator paymentCalculator;

    public PaymentController(PaymentCalculator paymentCalculator) {
//        this.paymentService = paymentService;
        this.paymentCalculator = paymentCalculator;
    }

    @PostMapping("/info")
    public ResponseEntity<ResponseRecord> getSum(@RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        ResponseRecord calculate = paymentCalculator.calculate(paymentRequestDTO);

        return ResponseEntity.ok().body(calculate);
    }

    public record ResponseRecord(long timeSpentInHours, BigDecimal amountTillNow) {

    }

}
