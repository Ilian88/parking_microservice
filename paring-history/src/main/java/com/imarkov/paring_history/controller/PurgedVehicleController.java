package com.imarkov.paring_history.controller;

import com.imarkov.paring_history.model.PurgedVehicleDTO;
import com.imarkov.paring_history.service.PurgedVehicleService;
import com.imarkov.paring_history.util.PageInfo;
import com.imarkov.paring_history.util.PageRequestWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/history")
public class PurgedVehicleController {
    private final PurgedVehicleService purgedVehicleService;

    public PurgedVehicleController(PurgedVehicleService purgedVehicleService) {
        this.purgedVehicleService = purgedVehicleService;
    }

    @PostMapping("/all")
    public Flux<PurgedVehicleDTO> getAllPageable(@RequestBody PageRequestWrapper pageInfo) {
        return purgedVehicleService.getAllRecords(pageInfo);
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
