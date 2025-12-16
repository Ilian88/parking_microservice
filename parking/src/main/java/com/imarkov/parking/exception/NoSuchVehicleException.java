package com.imarkov.parking.exception;

public class NoSuchVehicleException extends RuntimeException {
    public NoSuchVehicleException(String message) {
        super(message);
    }
}
