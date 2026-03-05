package com.imarkov.parking_logger;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
//@ConditionalOnProperty(
//        name = "parking.logger.consumer.enabled",
//        havingValue = "true",
//        matchIfMissing = false
//)
public class ParkingLogConsumer implements InitializingBean, DisposableBean {
    public final CountDownLatch startLatch = new CountDownLatch(1);
    public final List<ConsumerRecord<String, String>> stateRecords = new ArrayList<>();

    private KafkaConsumer<String, String> consumer;
    private Thread consumerThread;

//    @KafkaListener(
//            topics = "${parking.logger.topic}",
//            groupId = "${parking.logger.group-id}"
//    )
//    public void consume(LogEvent event) {
//        consumer.wakeup();
//    }

    @Override
    public void destroy() throws Exception {
        if (consumer != null) {
            consumer.wakeup(); // stops the poll loop
        }
    }

    @Override
    public void afterPropertiesSet() {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "parking-log-consumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        consumer = new KafkaConsumer<>(props);

        consumerThread = new Thread(()-> {
            System.out.println("Waiting for signal to consume the consumer");
            try {
                startLatch.await(1, TimeUnit.HOURS);
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               return;
            }

            try {
                while(true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        stateRecords.add(record);
                        System.out.println("<<<<<< Consumed: " + record.value());
                        // process the message
                        // commit manually if needed
                        consumer.commitSync();
                    }
                }
            } catch (WakeupException ex) {

            } finally {
                consumer.close();
            }

        }, "ParkingLogConsumerThread");
        consumerThread.start();
    }
}
