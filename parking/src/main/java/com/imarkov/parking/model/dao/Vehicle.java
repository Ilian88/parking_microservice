package com.imarkov.parking.model.dao;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle extends BaseEntity {
    @Column(nullable = false)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private EuroCategory euroCategory;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // needs @Transactional for reading when is lazy
    private ParkingSession parkingSession;

    public String getLicensePlate() {
        return licensePlate;
    }

    public Vehicle setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public EuroCategory getEuroCategory() {
        return euroCategory;
    }

    public Vehicle setEuroCategory(EuroCategory euroCategory) {
        this.euroCategory = euroCategory;
        return this;
    }

    public ParkingSession getParkingSession() {
        return parkingSession;
    }

    public Vehicle setParkingSession(ParkingSession parkingSession) {
        this.parkingSession = parkingSession;
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

    public enum VehicleType {
        CAR,
        TRUCK
    }
}
