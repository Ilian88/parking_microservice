package com.imarkov.paring_history.model;

import java.math.BigDecimal;

public record PurgedVehicleDTO (
     String licensePlate,
     String vehicleType,
     String euroCategory,
     long timeSpent,
     BigDecimal amountPayed,
     String currency) {}
