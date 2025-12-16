package com.imarkov.parking.model.dao;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Qualifier("car")
public class CarEntity extends Vehicle {
    String make;
    String model;

    @NotBlank
    public String getMake() {
        return make;
    }

    public CarEntity setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarEntity setModel(String model) {
        this.model = model;
        return this;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
