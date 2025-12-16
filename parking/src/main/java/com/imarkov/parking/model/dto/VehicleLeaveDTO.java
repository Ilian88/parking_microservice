package com.imarkov.parking.model.dto;

import com.imarkov.parking.model.CurrencyEnum;
import com.imarkov.parking.model.dao.Vehicle;

import java.math.BigDecimal;

public class VehicleLeaveDTO extends VehicleDTO {
    private BigDecimal paidAmount;
    private long timeSpent;
    private CurrencyEnum currency;
    private Vehicle.VehicleType vehicleType;

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public VehicleLeaveDTO setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
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
