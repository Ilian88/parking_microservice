package com.imarkov.paring_history.repo;

import com.imarkov.paring_history.model.PurgedVehicle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface PurgedVehicleRepository extends R2dbcRepository<PurgedVehicle, Long> {
    Flux<PurgedVehicle> findAllByLicensePlate(String licensePlate);
    Flux<PurgedVehicle> findAllBy(Pageable pageable);
    Mono<PurgedVehicle> findByLicensePlate(String licensePlate);
}
