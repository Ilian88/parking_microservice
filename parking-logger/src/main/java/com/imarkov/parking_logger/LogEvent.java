package com.imarkov.parking_logger;

import java.time.LocalDateTime;

public record LogEvent(String service, String level, String message,
                       String traceId, LocalDateTime timestamp) {
}
