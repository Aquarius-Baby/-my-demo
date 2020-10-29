package com.example.mykafkalearn.demo3;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/26 2:25 下午
 * @Description: 消费者
 */
@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"cmy_topic"})
    public void listen(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
        try {
            String message = (String) record.value();
            System.out.println(String.format("demo2 KafkaConsumer 消费 ====> %s", message));
            Thread.sleep(5000);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("demo3 cmy_topic 消费失败。。。。");
        }
    }
}