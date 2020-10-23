package com.example.myrabbitmqlearn.test;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/23 2:30 下午
 * @Description:
 */
@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String content) {
        rabbitTemplate.convertAndSend(content);
        System.out.println("发送消息: '" + content + "'");
    }
}
