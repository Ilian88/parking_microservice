package logging.kafka.lib.log;

import java.time.LocalDateTime;

public record LogEvent(String service, String level, String message,
                       String traceId, LocalDateTime timestamp) {
}
