package com.imarkov.parking.exception;

import java.time.LocalDateTime;

public record ApiError(LocalDateTime now, int status, String message) { }
