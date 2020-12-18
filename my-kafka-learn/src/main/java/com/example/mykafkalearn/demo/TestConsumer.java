package com.example.mykafkalearn.demo;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class TestConsumer {

//    @KafkaListener(topics = {"${spring.kafka.kafka-consumer1.topic}"}, groupId = "${spring.kafka.kafka-consumer1.group-id}")
    public void receive(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println(String.format("demo consumer 消费 ====> %s" , message));
            acknowledgment.acknowledge();
        }
    }
}