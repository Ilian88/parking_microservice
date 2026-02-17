package logging.kafka.lib.log;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaProducer {
    private  final KafkaTemplate<String, LogEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, LogEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInBatch(List<LogEvent> logEventList) {
        logEventList.forEach(e -> kafkaTemplate.send("parking-logs", e.service(), e));
    }
}
