package com.imarkov.parking.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
public class ParkingBooking extends BaseEntity {
    @Column(nullable = false)
    private LocalDateTime bookingDateTime;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Enumerated(value = EnumType.STRING)
    private Vehicle.EuroCategory euroCategory;

    @Enumerated(value = EnumType.STRING)
    private Vehicle.VehicleType vehicleType;

    public ParkingBooking() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public ParkingBooking setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public Vehicle.EuroCategory getEuroCategory() {
        return euroCategory;
    }

    public ParkingBooking setEuroCategory(Vehicle.EuroCategory euroCategory) {
        this.euroCategory = euroCategory;
        return this;
    }

    public Vehicle.VehicleType getVehicleType() {
        return vehicleType;
    }

    public ParkingBooking setVehicleType(Vehicle.VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public ParkingBooking setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
        return this;
    }
}
