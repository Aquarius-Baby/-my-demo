package com.example.mykafkalearn.util;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/27 1:54 下午
 * @Description:
 */
@Component
public class KafkaUtils {

    private static final Logger logger = LoggerFactory.getLogger(KafkaUtils.class);

    @Resource(name = "kafkaTemplate1")
    private KafkaProducer<String, String> kafkaTemplate1;

    public boolean sendMessage(String topicName, Integer partition, String key, String value) {
        try {
            logger.info(String.format("向kafka中生产消息 topicName:%s, partition:%s, key:%s ", topicName, partition, key));
            Future<RecordMetadata> res = kafkaTemplate1.send(new ProducerRecord<>(topicName, partition, key, value));
            return true;
        } catch (Exception e) {
            logger.error("发送topic消息体失败：{}", value);
            return false;
        }
    }

}