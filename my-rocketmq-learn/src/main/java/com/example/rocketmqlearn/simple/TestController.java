package com.example.rocketmqlearn.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/23 2:50 下午
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Autowired
    private ParamConfigService paramConfigService;

    @GetMapping("/testStringQueue")
    public SendResult testStringQueue() {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.platGroup);
        SendResult sendResult = null;
        String msgInfo = "rocketmq  message 1";
        try {
            Message sendMsg = new Message(paramConfigService.platTopic,
                    paramConfigService.accountTag, msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }

}
