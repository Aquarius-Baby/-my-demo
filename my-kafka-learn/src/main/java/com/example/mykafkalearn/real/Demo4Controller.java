package com.example.mykafkalearn.real;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo4")
public class Demo4Controller {

    @Autowired
    private KafkaProducer2 kafkaProducer2;

    @GetMapping("/send")
    @ResponseBody
    public String send(@RequestParam(value = "topic") String topic,
                       @RequestParam(value = "message") String message) {
        kafkaProducer2.sendMessage(topic, message);
        return "发送消息成功";
    }

}