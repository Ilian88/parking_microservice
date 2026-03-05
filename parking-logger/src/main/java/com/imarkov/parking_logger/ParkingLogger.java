package com.imarkov.parking_logger;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingLogger {
    private final LogBuffer buffer;
    private final String service;

    public ParkingLogger(LogBuffer buffer, String service) {
        this.buffer = buffer;
        this.service = service;
    }

    public void info(String msg) {
        log("INFO", msg);
    }

    public void warn(String msg) {
        log("WARN", msg);
    }

    public void error(String msg) {
        log("ERROR", msg);
    }

    private void log(String level, String msg) {
        buffer.add(new LogEvent(
                service,
                level,
                msg,
                UUID.randomUUID().toString(),
                LocalDateTime.now()
        ));
    }
}
