package com.imarkov.parking.model.dto;

import com.imarkov.parking.model.dao.Vehicle;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class VehicleDTO {
    @NotNull(message = "license is mandatory")
    private String licensePlate;
    private LocalDateTime enteredAt;
    private LocalDateTime leftAt;
    @NotNull(message = "euro category is mandatory")
    private Vehicle.EuroCategory euroCategory;

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleDTO setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    public VehicleDTO setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
        return this;
    }

    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public VehicleDTO setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
        return this;
    }

    public Vehicle.EuroCategory getEuroCategory() {
        return euroCategory;
    }

    public VehicleDTO setEuroCategory(Vehicle.EuroCategory euroCategory) {
        this.euroCategory = euroCategory;
        return this;
    }
}
