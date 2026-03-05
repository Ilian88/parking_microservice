package com.imarkov.parking_logger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@ConditionalOnProperty(
//        name = "parking.logger.producer.enabled",
//        havingValue = "true",
//        matchIfMissing = false
//)
public class ParkingLogProducer {
    private  final KafkaTemplate<String, LogEvent> kafkaTemplate;

    public ParkingLogProducer(KafkaTemplate<String, LogEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInBatch(List<LogEvent> logEventList) {
        logEventList.forEach(e -> kafkaTemplate.send("parking-logs", (String) e.service(),(LogEvent) e));
    }
}
