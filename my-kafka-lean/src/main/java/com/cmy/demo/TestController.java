package com.cmy.kafaka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestProducer testProducer;

    @GetMapping("/send")
    @ResponseBody
    public String send(@RequestParam(value = "msg") String msg) {
        testProducer.send(msg);
        return "发送消息成功";
    }

}