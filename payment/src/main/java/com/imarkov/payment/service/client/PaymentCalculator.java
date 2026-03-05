package com.imarkov.payment.service.client;

import com.imarkov.payment.controller.PaymentController;
import com.imarkov.payment.model.dto.StayDetails;

public interface PaymentCalculator {
    PaymentController.ResponseRecord calculate(StayDetails stayDetails);
}
