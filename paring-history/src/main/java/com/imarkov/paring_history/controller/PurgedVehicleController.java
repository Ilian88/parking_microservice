package com.imarkov.paring_history.controller;

import com.imarkov.paring_history.model.PurgedVehicleDTO;
import com.imarkov.paring_history.service.PurgedVehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/history")
public class PurgedVehicleController {
    private final PurgedVehicleService purgedVehicleService;

    public PurgedVehicleController(PurgedVehicleService purgedVehicleService) {
        this.purgedVehicleService = purgedVehicleService;
    }

    @PostMapping("/create")
    Mono<ResponseEntity<Long>> createPurgedVehicle(@RequestBody PurgedVehicleDTO dto) {
        return purgedVehicleService
                .createVehicle(dto)
                .map(saved -> ResponseEntity
                        .created(UriComponentsBuilder.fromUri(URI.create("/history"))
                                .queryParam("licensePlate", saved.getLicensePlate()).build()
                                .toUri())
                        .body(saved.getId())
                );
    }
}
