package com.example.mykafkalearn.real;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class TestController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send")
    @ResponseBody
    public String send(@RequestParam(value = "topic") String topic,
                       @RequestParam(value = "key") String key,
                       @RequestParam(value = "message") String message) {
        kafkaProducer.sendMessage(topic, key, message);
        return "发送消息成功";
    }

}