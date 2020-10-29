package com.example.mykafkalearn.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/26 2:27 下午
 * @Description: 生产者
 */

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendLog(String log) {
        System.err.println("向kafka中生产消息:" + log);
        kafkaTemplate.send("cmy_topic", log);
        kafkaTemplate.send("test", log);
    }
}
