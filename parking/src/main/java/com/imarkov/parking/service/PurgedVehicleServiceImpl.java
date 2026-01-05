package com.imarkov.parking.service;

import com.imarkov.parking.model.PurgedVehicle;
import com.imarkov.parking.model.dto.VehicleDTO;
import com.imarkov.parking.repo.PurgedVehicleRepo;
import com.imarkov.parking.service.client.PurgedVehicleService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PurgedVehicleServiceImpl implements PurgedVehicleService {
    private final PurgedVehicleRepo purgedVehicleRepo;

    public PurgedVehicleServiceImpl(PurgedVehicleRepo purgedVehicleRepo) {
        this.purgedVehicleRepo = purgedVehicleRepo;
    }

    @Override
    public List<VehicleDTO> findRecordsBetween(LocalDate start, LocalDate end) {
        LocalDateTime startWithTime = start.atStartOfDay();
        LocalDateTime endWithTime = end.atTime(LocalTime.MAX);

        return this.purgedVehicleRepo.findByParkingSession_EnteredAtBetween(startWithTime, endWithTime).orElse(new ArrayList<>())
                .stream()
                .map(vehicle -> new VehicleDTO()
                        .setLicensePlate(vehicle.getLicensePlate())
                        .setEnteredAt(vehicle.getParkingSession().getEnteredAt())
                        .setLeftAt(vehicle.getParkingSession().getLeftAt())
                        .setEuroCategory(vehicle.getEuroCategory())
                ).toList();

    }
}
