package com.imarkov.parking.schedule;

import com.imarkov.parking.custom.ThreadExecPool;
import com.imarkov.parking.model.PurgedVehicle;
import com.imarkov.parking.model.dao.CarEntity;
import com.imarkov.parking.model.dao.Vehicle;
import com.imarkov.parking.repo.PurgedVehicleRepo;
import com.imarkov.parking.repo.VehicleRepo;
import com.imarkov.parking.service.VehicleCleanupService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VehiclesCleanScheduler {
    private final VehicleCleanupService vehicleCleanupService;

    public VehiclesCleanScheduler(VehicleRepo vehicleRepo, PurgedVehicleRepo purgedVehicleRepo, VehicleCleanupService vehicleCleanupService) {
        this.vehicleCleanupService = vehicleCleanupService;
    }


//    @Scheduled(fixedRate = 15000)
    public void cleanupLeftVehicles() {
        ThreadExecPool threadExecPool = ThreadExecPool.getInstance();
        threadExecPool.submit(new CleanupDatabase(vehicleCleanupService));

    }

    private record CleanupDatabase(VehicleCleanupService vehicleCleanupService) implements Runnable {
        @Override
            public void run() {
                vehicleCleanupService.executeCleanupLogic();
            }
        }
}
