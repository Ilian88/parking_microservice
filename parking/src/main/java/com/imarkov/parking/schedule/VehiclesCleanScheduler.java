package com.imarkov.parking.schedule;

import com.imarkov.parking.custom.ThreadExecPool;
import com.imarkov.parking.repo.PurgedVehicleRepo;
import com.imarkov.parking.repo.VehicleRepo;
import com.imarkov.parking.service.VehicleCleanupService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class VehiclesCleanScheduler {
    private final VehicleCleanupService vehicleCleanupService;

    public VehiclesCleanScheduler(VehicleRepo vehicleRepo, PurgedVehicleRepo purgedVehicleRepo, VehicleCleanupService vehicleCleanupService) {
        this.vehicleCleanupService = vehicleCleanupService;
    }


    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
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
