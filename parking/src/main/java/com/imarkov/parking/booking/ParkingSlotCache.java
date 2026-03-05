package com.imarkov.parking.booking;

import com.imarkov.parking.properties.AppProperties;
import com.imarkov.parking.service.client.VehicleService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ParkingSlotCache {
    private final AtomicInteger availableSlots = new AtomicInteger();
    private final VehicleService vehicleService;
    private final AppProperties appProperties;

    public ParkingSlotCache(VehicleService vehicleService, AppProperties appProperties) {
        this.vehicleService = vehicleService;
        this.appProperties = appProperties;
        if (vehicleService.getAllCurrent().size() > appProperties.getCapacity()) {
            throw new RuntimeException("Vehicles in the parking can't be greater than the parking capacity");
        }
    }

    @PostConstruct
    public void calculateAvailableSlots() {
        int available = appProperties.getCapacity() - vehicleService.getAllCurrent().size();
        availableSlots.set(available);
    }

    public int getAvailableSlots() {
        return availableSlots.get();
    }

    public int addSlot() {
        return availableSlots.decrementAndGet();
    }

    public int releaseSlot() {
        return availableSlots.incrementAndGet();
    }
}
