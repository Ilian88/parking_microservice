package com.imarkov.parking.service;

import com.imarkov.parking.model.PurgedVehicle;
import com.imarkov.parking.model.dao.CarEntity;
import com.imarkov.parking.model.dao.Vehicle;
import com.imarkov.parking.repo.PurgedVehicleRepo;
import com.imarkov.parking.repo.VehicleRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleCleanupService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleCleanupService.class);

    private final VehicleRepo vehicleRepo;
    private final PurgedVehicleRepo purgedVehicleRepo;

    public VehicleCleanupService(VehicleRepo vehicleRepo, PurgedVehicleRepo purgedVehicleRepo) {
        this.vehicleRepo = vehicleRepo;
        this.purgedVehicleRepo = purgedVehicleRepo;
    }

    @Transactional
    @Modifying
    public void executeCleanupLogic() {
        List<Vehicle> allVehicleLeft = vehicleRepo.findAllVehicleLeft();
        List<PurgedVehicle> purgedVehicles = new ArrayList<>();

        for (Vehicle vehicle : allVehicleLeft) {
            PurgedVehicle purgedVehicle = new PurgedVehicle();
            purgedVehicle
                    .setLicensePlate(vehicle.getLicensePlate())
                    .setTimeSpent(calculateTimeSpent(vehicle.getParkingSession().getEnteredAt(), vehicle.getParkingSession().getLeftAt()))
                    .setVehicleType(vehicle instanceof CarEntity ? Vehicle.VehicleType.CAR.toString() : Vehicle.VehicleType.TRUCK.toString())
                    .setEuroCategory(vehicle.getEuroCategory())
                    .setParkingSession(vehicle.getParkingSession());

            purgedVehicles.add(purgedVehicle);

            vehicle.setParkingSession(null);

            logger.info("Vehicle to be purged: {}", vehicle.getLicensePlate());
        }

        if (!allVehicleLeft.isEmpty()) {
            purgedVehicleRepo.saveAllAndFlush(purgedVehicles);
            vehicleRepo.saveAllAndFlush(allVehicleLeft);

            vehicleRepo.deleteAll(allVehicleLeft);
            logger.info("Successfully purged {} vehicles that are no longer in the parking", purgedVehicles.size());
        }
    }

    private long calculateTimeSpent(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.HOURS.between(start, end);
    }
}
