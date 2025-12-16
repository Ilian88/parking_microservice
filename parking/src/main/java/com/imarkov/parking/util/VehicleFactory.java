package com.imarkov.parking.util;

import com.imarkov.parking.model.dao.CarEntity;
import com.imarkov.parking.model.dao.TruckEntity;
import com.imarkov.parking.model.dao.Vehicle;

import java.util.function.Supplier;

public enum VehicleFactory {
    CAR(CarEntity::new),
    TRUCK(TruckEntity::new);

    private final Supplier<Vehicle> constructor;

    VehicleFactory(Supplier<Vehicle> constructor) {
        this.constructor = constructor;
    }

    public Vehicle create() {
        return constructor.get();
    }
}

