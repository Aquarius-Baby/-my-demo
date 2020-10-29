package com.example.mykafkalearn.real;

import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/26 2:27 下午
 * @Description: 生产者
 */

@Component
public class KafkaProducer2 {

    @Resource
    private KafkaUtils kafkaUtils;

    public void sendMessage(String topic, String message) {
        System.out.println("向kafka中生产消息:" + message);
        kafkaUtils.sendMessage(topic, message);
    }
}
