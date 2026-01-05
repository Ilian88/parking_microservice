package com.imarkov.paring_history.service;

import com.imarkov.paring_history.model.PurgedVehicle;
import com.imarkov.paring_history.model.PurgedVehicleDTO;
import com.imarkov.paring_history.repo.PurgedVehicleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PurgedVehicleServiceImpl implements PurgedVehicleService {
    private final PurgedVehicleRepository vehicleRepository;

    public PurgedVehicleServiceImpl(PurgedVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Mono<PurgedVehicle> createVehicle(PurgedVehicleDTO vehicleDTO) {
        PurgedVehicle vehicle = creteEntityFromDto(vehicleDTO);

        return vehicleRepository.save(vehicle);
    }

    private static PurgedVehicle creteEntityFromDto(PurgedVehicleDTO vehicleDTO) {
        return new PurgedVehicle()
                .setLicensePlate(vehicleDTO.licensePlate())
                .setVehicleType(vehicleDTO.vehicleType())
                .setCurrency(vehicleDTO.currency())
                .setEuroCategory(vehicleDTO.euroCategory())
                .setAmountPayed(vehicleDTO.amountPayed())
                .setTimeSpent(vehicleDTO.timeSpent());
    }
}
