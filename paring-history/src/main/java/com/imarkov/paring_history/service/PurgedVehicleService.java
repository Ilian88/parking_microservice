package com.imarkov.paring_history.service;

import com.imarkov.paring_history.model.PurgedVehicle;
import com.imarkov.paring_history.model.PurgedVehicleDTO;
import reactor.core.publisher.Mono;

public interface PurgedVehicleService {
    Mono<PurgedVehicle> createVehicle(PurgedVehicleDTO vehicleDTO);
}
