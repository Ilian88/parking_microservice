package com.imarkov.parking.controller;

import com.imarkov.parking.exception.ApiError;
import com.imarkov.parking.exception.AuthException;
import com.imarkov.parking.exception.NoSuchVehicleException;
import com.imarkov.parking.exception.VehicleAlreadyExistsException;
import jakarta.annotation.Nonnull;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalAdviceHandler {

    @ExceptionHandler(value = {NoSuchVehicleException.class, VehicleAlreadyExistsException.class})
    public ResponseEntity<ApiError> handleVehicleException(Exception ex) {
        return badRequest(ex.getMessage());
    }

    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleValidationException(
            Exception ex) {

        if (ex instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            List<String> errors = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                    .toList();

            return badRequest(String.join(" ---- ", errors));
        }

        return badRequest(ex.getMessage());
    }

    @ExceptionHandler(value = {AuthException.class})
    public ResponseEntity<ApiError> handleAuthException(Exception ex) {
        return badRequest(ex.getMessage());
    }

    private ResponseEntity<ApiError> badRequest(String ex) {
        return ResponseEntity.badRequest().body(
                new ApiError(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        ex
                )
        );
    }
}
