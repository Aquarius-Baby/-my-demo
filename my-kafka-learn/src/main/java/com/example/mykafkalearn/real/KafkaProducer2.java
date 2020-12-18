package com.example.mykafkalearn.real;

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

    public void sendMessage(String topic, String key, String message) {
        int partition = 0;
        switch (key) {
            case "tke_01":
                partition = 0;
                break;
            case "tke_02":
                partition = 1;
                break;
            case "tke_03":
                partition = 2;
                break;
            default:
                break;
        }
        kafkaUtils.sendMessage(topic, partition, key, message);
    }
}
