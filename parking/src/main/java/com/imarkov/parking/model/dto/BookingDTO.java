package com.imarkov.parking.model.dto;

import com.imarkov.parking.model.dao.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class BookingDTO {
    private LocalDateTime bookingDateTime;
    private String licensePlate;
    private Vehicle.EuroCategory euroCategory;
    private Vehicle.VehicleType vehicleType;

    @NotNull
    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public BookingDTO setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
        return this;
    }

    @NotBlank
    public String getLicensePlate() {
        return licensePlate;
    }

    public BookingDTO setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public Vehicle.EuroCategory getEuroCategory() {
        return euroCategory;
    }

    public BookingDTO setEuroCategory(Vehicle.EuroCategory euroCategory) {
        this.euroCategory = euroCategory;
        return this;
    }

    @NotBlank
    public Vehicle.VehicleType getVehicleType() {
        return vehicleType;
    }

    public BookingDTO setVehicleType(Vehicle.VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }
}
