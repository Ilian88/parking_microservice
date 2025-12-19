package com.imarkov.paring_history.repo;

import com.imarkov.paring_history.model.PurgedVehicle;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurgedVehicleRepository extends R2dbcRepository<PurgedVehicle, Long> {
    Flux<PurgedVehicle> findAllByLicensePlate(String licensePlate);
    Mono<PurgedVehicle> findByLicensePlate(String licensePlate);
}
