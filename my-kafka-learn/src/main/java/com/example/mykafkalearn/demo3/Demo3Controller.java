package com.example.mykafkalearn.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test2")
public class Demo3Controller {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send")
    @ResponseBody
    public String send(@RequestParam(value = "msg") String msg) {
        kafkaProducer.sendLog(msg);
        return "发送消息成功";
    }

}