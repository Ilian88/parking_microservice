package com.imarkov.parking.service.client;

import com.imarkov.parking.model.dto.VehicleDTO;

import java.time.LocalDate;
import java.util.List;

public interface PurgedVehicleService {
    List<VehicleDTO> findRecordsBetween (LocalDate start, LocalDate end);
}
