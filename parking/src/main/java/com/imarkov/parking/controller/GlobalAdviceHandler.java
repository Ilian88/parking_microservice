package com.imarkov.parking.controller;

import com.imarkov.parking.exception.ApiError;
import com.imarkov.parking.exception.NoSuchVehicleException;
import com.imarkov.parking.exception.VehicleAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalAdviceHandler {

    @ExceptionHandler(value = {NoSuchVehicleException.class, VehicleAlreadyExistsException.class})
    public ResponseEntity<ApiError> handleVehicleException(Exception ex) {
        return ResponseEntity.badRequest().body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()
                )
        );
    }
}
