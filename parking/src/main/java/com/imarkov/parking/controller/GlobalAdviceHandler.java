package com.imarkov.parking.controller;

import com.imarkov.parking.exception.ApiError;
import com.imarkov.parking.exception.NoSuchVehicleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalAdviceHandler {

    @ExceptionHandler(NoSuchVehicleException.class)
    public ResponseEntity<ApiError> handleDuplicateItem(String message) {
        return ResponseEntity.badRequest().body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        message
                )
        );
    }
}
