package com.imarkov.paring_history.service;

import com.imarkov.paring_history.model.PurgedVehicle;
import com.imarkov.paring_history.model.PurgedVehicleDTO;
import com.imarkov.paring_history.util.PageRequestWrapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurgedVehicleService {
    Mono<PurgedVehicle> createVehicle(PurgedVehicleDTO vehicleDTO);
    Flux<PurgedVehicleDTO> getAllRecords(PageRequestWrapper<Void> pageInfo);
    Flux<PurgedVehicleDTO> getAllByLicensePlate(PageRequestWrapper<String> pageInfo);
}
