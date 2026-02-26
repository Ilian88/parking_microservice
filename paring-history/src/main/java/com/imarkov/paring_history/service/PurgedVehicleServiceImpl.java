package com.imarkov.paring_history.service;

import com.imarkov.paring_history.model.PurgedVehicle;
import com.imarkov.paring_history.model.PurgedVehicleDTO;
import com.imarkov.paring_history.repo.PurgedVehicleRepository;
import com.imarkov.paring_history.util.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;


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

    @Override
    public Flux<PurgedVehicleDTO> getAllRecords(PageInfo pageInfo) {
        String directionString = (pageInfo.direction() == null) ? "asc" : pageInfo.direction();
        Sort.Direction direction = Sort.Direction.valueOf(directionString.toUpperCase());

        Pageable pageRequest = PageRequest.of(
                pageInfo.page(),
                pageInfo.size(),
                direction,
                pageInfo.sortBy() == null ? "licensePlate" : pageInfo.sortBy()
        );
        return this.vehicleRepository
                .findAllBy(pageRequest)
                .map(this::toDTO);
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

    private PurgedVehicleDTO toDTO(PurgedVehicle vehicle) {
        return new PurgedVehicleDTO(
                vehicle.getLicensePlate(),
                vehicle.getVehicleType(),
                vehicle.getEuroCategory(),
                vehicle.getTimeSpent(),
                vehicle.getAmountPayed(),
                vehicle.getCurrency()
        );
    }
}
