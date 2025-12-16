package com.imarkov.parking.model.dao;

import java.math.BigDecimal;

public record PaymentInfoDTO(String licensePlate, long timeSpentInHours,
                          BigDecimal amountToPay, Vehicle.EuroCategory euroCategory) {
}
