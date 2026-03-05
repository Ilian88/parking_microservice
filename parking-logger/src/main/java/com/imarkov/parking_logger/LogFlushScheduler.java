package com.imarkov.parking_logger;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

//@EnableScheduling
//@Component
public class LogFlushScheduler {
    private final LogBuffer logBuffer;
    private final ParkingLogProducer kafkaProducer;

    public LogFlushScheduler(LogBuffer logBuffer, ParkingLogProducer kafkaProducer) {
        this.logBuffer = logBuffer;
        this.kafkaProducer = kafkaProducer;
    }

//    @Scheduled(fixedRate = 500)
    private void flush() {
        List<LogEvent> logEventsBatch = logBuffer.drain();
        if (!logEventsBatch.isEmpty()) {
            kafkaProducer.sendInBatch(logEventsBatch);
        }
    }
}
