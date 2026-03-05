package com.imarkov.parking.model.dao;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ParkingSession extends BaseEntity {
    @Column(nullable = false)
    private LocalDateTime enteredAt;
    private LocalDateTime leftAt;
    private BigDecimal amountToPay;

//    @OneToOne
//    @JoinColumn(name = "vehicle_id")
//    private Vehicle vehicle;

    public ParkingSession (){
        this.enteredAt = LocalDateTime.now();
    }

//    public Vehicle getVehicle() {
//        return vehicle;
//    }
//
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }

    public LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    public void setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
    }

    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
    }

    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public ParkingSession setAmountToPay(BigDecimal amountToPay) {
        this.amountToPay = amountToPay;
        return this;
    }
}
