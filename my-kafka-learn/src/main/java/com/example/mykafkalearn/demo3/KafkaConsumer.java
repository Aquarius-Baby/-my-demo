//package com.example.mykafkalearn.demo3;
//
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: cmy
// * @Date: Created in  2020/10/26 2:25 下午
// * @Description: 消费者
// */
//@Component
//public class KafkaConsumer {
//
//    @KafkaListener(topics = {"my_topic_partitions"}, groupId = "test")
//    public void listen(ConsumerRecord record, Acknowledgment acknowledgment) {
//        try {
//            System.out.println("============开始消费==============");
//            Object message = record.value();
//            System.out.println(String.format("partition : %s, key : %s, message : %s ", record.partition(), record.key(), message));
//            Thread.sleep(5000);
//            acknowledgment.acknowledge();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("demo3 cmy_topic 消费失败。。。。");
//        }
//    }
////
////    @KafkaListener(containerGroup = "test", topicPartitions = {
////            @TopicPartition(topic = "my_topic_partitions", partitions = {"0"}),
////
////    })
////    public void onMessage0(ConsumerRecord record, Acknowledgment acknowledgment) {
////        System.out.println("我是分区 0：" + record.topic() + "-" + record.partition() + "-" + record.value());
////        acknowledgment.acknowledge();
////    }
////
////    @KafkaListener(containerGroup = "test", topicPartitions = {
////            @TopicPartition(topic = "my_topic_partitions", partitions = {"1", "2"})
////    })
////    public void onMessage1(ConsumerRecord record, Acknowledgment acknowledgment) {
////        System.out.println("我是分区 1：" + record.topic() + "-" + record.partition() + "-" + record.value());
////        acknowledgment.acknowledge();
////
////    }
////
////    @KafkaListener(containerGroup = "test", topicPartitions = {
////            @TopicPartition(topic = "my_topic_partitions", partitions = {"2"})
////    })
////    public void onMessage2(ConsumerRecord record, Acknowledgment acknowledgment) {
////        System.out.println("我是分区 2：" + record.topic() + "-" + record.partition() + "-" + record.value());
////        acknowledgment.acknowledge();
////
////    }
//
//}