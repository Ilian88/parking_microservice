package com.imarkov.parking.controller;

import com.imarkov.parking.exception.ApiError;
import com.imarkov.parking.model.dao.PaymentInfoDTO;
import com.imarkov.parking.model.dto.VehicleCreatedDTO;
import com.imarkov.parking.model.dto.VehicleEnterDTO;
import com.imarkov.parking.model.dto.VehicleGeneralDTO;
import com.imarkov.parking.service.client.VehicleService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/current/all")
    public List<VehicleGeneralDTO> getAllCurrent() {
        return vehicleService.getAllCurrent();
    }

    @PostMapping("/create")
    public ResponseEntity<VehicleCreatedDTO> vehicleEnters(@RequestBody VehicleEnterDTO vehicleCreateDTO) {
        VehicleCreatedDTO vehicle = vehicleService.createVehicle(vehicleCreateDTO);

        return ResponseEntity.ok().body(vehicle);
    }

    @GetMapping("/vehicle/sum")
    public ResponseEntity<PaymentInfoDTO> requestInfo(@RequestParam String licensePlate) {
        return ResponseEntity.ok().body(vehicleService.getPaymentInfo(licensePlate));
    }

    @PostMapping("/vehicle/leave")
    public ResponseEntity<Void> requestLeave(@RequestParam String licensePlate) {
        vehicleService.requestLeave(licensePlate);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleEntityExists(DataIntegrityViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.CONTINUE.value(),
                        ex.getCause().getLocalizedMessage()
                ));
    }
}
