package com.imarkov.parking_logger;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest(properties = {
        "spring.config.name=application-test",
        "spring.kafka.bootstrap-servers=localhost:9092",
        "parking.logger.topic=parking-logs," +
        "parking.logger.producer.enabled=true" +
        "parking.logger.consumer.enabled=true"})
public class KafkaTest {
    @Autowired ParkingLogProducer producer;
    @Autowired ParkingLogConsumer consumer;
    private final LogEvent logEvent = new LogEvent("test", LogLevel.INFO.name() ,
            "message from test producer", "some trace id", LocalDateTime.now());

    @Test
    public void test() throws InterruptedException {
        producer.sendInBatch(List.of(logEvent));
        Thread.currentThread().sleep(Duration.of(10, TimeUnit.SECONDS.toChronoUnit()));

        consumer.startLatch.countDown();

        Assert.isTrue(!consumer.stateRecords.isEmpty(), "0");
    }

}
