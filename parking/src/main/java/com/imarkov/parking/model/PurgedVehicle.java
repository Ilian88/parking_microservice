package com.imarkov.parking.model;

import com.imarkov.parking.model.dao.BaseEntity;
import com.imarkov.parking.model.dao.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

@Entity
public class PurgedVehicle extends BaseEntity {
    private String licensePlate;
    private String vehicleType;
    private Vehicle.EuroCategory euroCategory;
    private long timeSpent;
    private BigDecimal amountPayed;
    @Enumerated(value = EnumType.STRING)
    private CurrencyEnum currency;

    public String getLicensePlate() {
        return licensePlate;
    }

    public PurgedVehicle setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public PurgedVehicle setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public Vehicle.EuroCategory getEuroCategory() {
        return euroCategory;
    }

    public PurgedVehicle setEuroCategory(Vehicle.EuroCategory euroCategory) {
        this.euroCategory = euroCategory;
        return this;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public PurgedVehicle setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
        return this;
    }

    public BigDecimal getAmountPayed() {
        return amountPayed;
    }

    public PurgedVehicle setAmountPayed(BigDecimal amountPayed) {
        this.amountPayed = amountPayed;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public PurgedVehicle setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }
}
