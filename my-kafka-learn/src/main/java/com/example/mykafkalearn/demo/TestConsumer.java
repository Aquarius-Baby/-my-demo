package com.example.mykafkalearn.demo;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class TestConsumer {

    @KafkaListener(topics = {"test"})
    public void receive(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("receive record: " + record);
            System.out.println("receive message: " + message);
        }
    }
}