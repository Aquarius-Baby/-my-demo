package com.example.mykafkalearn.demo;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String msg) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        System.out.println("send: " + JSONObject.toJSONString(message));
        kafkaTemplate.send("test", JSONObject.toJSONString(message));
        Math.pow(3, 10);
    }
}