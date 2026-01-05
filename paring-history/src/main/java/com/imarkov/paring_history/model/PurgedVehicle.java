package com.imarkov.paring_history.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("purged_vehicle")
public class PurgedVehicle {
    @Id
    private long id;
    private String licensePlate;
    private String vehicleType;
    private String euroCategory;
    private long timeSpent;
    private BigDecimal amountPayed;
    private String currency;

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

    public String getEuroCategory() {
        return euroCategory;
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

    public PurgedVehicle setEuroCategory(String euroCategory) {
        this.euroCategory = euroCategory;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public PurgedVehicle setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public long getId() {
        return id;
    }

    public PurgedVehicle setId(long id) {
        this.id = id;
        return this;
    }

    public enum EuroCategory {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX
    }

    public enum Currency {
        BGN,
        EUR
    }
}
