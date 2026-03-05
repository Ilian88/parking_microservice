package com.imarkov.parking.model.dto;

import com.imarkov.parking.model.CurrencyEnum;
import com.imarkov.parking.model.dao.Vehicle;

import java.math.BigDecimal;

public class VehicleLeaveDTO extends VehicleDTO {
    private BigDecimal amountToPay;
    private long timeSpent;
    private CurrencyEnum currency;
    private Vehicle.VehicleType vehicleType;

    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public VehicleLeaveDTO setAmountToPay(BigDecimal amountToPay) {
        this.amountToPay = amountToPay;
        return this;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public VehicleLeaveDTO setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public VehicleLeaveDTO setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public Vehicle.VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleLeaveDTO setVehicleType(Vehicle.VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }
}
