# 1. 搭建 zk 及 kafka
docker 下载镜像
wurstmeister/kafka
wurstmeister/zookeeper


启动服务
```
docker run -d --name zookeeper -p 2181:2181 -v /etc/localtime:/etc/localtime wurstmeister/zookeeper
docker run -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT={YOUR IP}:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://{YOUR IP}:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -t wurstmeister/kafka
```

# 2. 创建topic

进入kafka容器

``` 
查看所有topic
/opt/kafka/bin/kafka-topics.sh --list --bootstrap-server {YOUR IP}:9092

创建 topic, 副本、partion 数目
/opt/kafka/bin/kafka-topics.sh --bootstrap-server {YOUR IP}:9092 --create --replication-factor 1 --partitions 5 --topic dev_topic

修改topic
/opt/kafka/bin/kafka-topics.sh --bootstrap-server {YOUR IP}:9092 --alter --partitions 5 --topic dev_topic

删除topic
/opt/kafka/bin/kafka-topics.sh --bootstrap-server {YOUR IP}:9092 --delete --topic dev_topic

```
# 3. 代码配置

```
spring:
  kafka:
    bootstrap-servers: 10.101.60.32:9092
    listener:
      concurrency: 3
      ack-mode: MANUAL_IMMEDIATE
      poll-timeout: 1500
    #默认的消费者配置
    consumer:
      group-id: myGroup
      topic: dev_topic
      # earliest ：在偏移量无效的情况下，消费者将从起始位置读取分区的记录
      auto-offset-reset: latest
      # 是否自动提交偏移量，默认值是true,为了避免出现重复数据和数据丢失，可以把它设置为false,然后手动提交偏移量
      enable-auto-commit: false
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      properties: {session.timeout.ms: 6000, auto.offset.reset: earliest}
      session-timeout: 12

    #默认的生产者配置
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
      batch-size: 65536
      buffer-memory: 524288
      retries: 0
      servers: 10.101.60.32:9092
      acks: all   
```

# 4. 生产者代码

```
    private KafkaUtils kafkaUtils;
    public void sendMessage(String topic, String key, String message) {
        int partition = 0;
        switch (key) {
            case "black":
                partition = 0;
                break;
            case "white":
                partition = 1;
                break;
            case "red":
                partition = 2;
                break;
            default:
                break;
        }
        kafkaUtils.sendMessage(topic, partition, key, message);
    }

```

# 5. 消费者代码
```
    @KafkaListener(topics = {"${spring.kafka.consumer.topic}"}, groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            Object message = record.value();
            System.out.println(String.format("%s 我是分区: %s, topic: %s, key : %s, message : %s ", System.currentTimeMillis(), record.partition(), record.topic(), record.key(), message));
            Thread.sleep(5000);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("demo3 cmy_topic 消费失败。。。。");
        }
    }
```

# 6. utils

kafka 工具类
```java
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
```

建立kafka连接的配置

```java
@Component
public class KafkaConfiguration {

    @Bean(name = "kafkaTemplate1")
    public KafkaProducer<String, String> kafkaTemplate1(@Value("${spring.kafka.producer.servers}") String servers,
                                                        @Value("${spring.kafka.producer.acks}") String acks,
                                                        @Value("${spring.kafka.producer.retries}") String retries,
                                                        @Value("${spring.kafka.producer.buffer-memory}") Long memory,
                                                        @Value("${spring.kafka.producer.key-serializer}") String keySerializer,
                                                        @Value("${spring.kafka.producer.value-serializer}") String valueSerializer) {
        Properties props = new Properties();
        //broker地址
        props.put("bootstrap.servers", servers);
        //请求时候需要验证
        props.put("acks", acks);
        //请求失败时候需要重试
        props.put("retries", retries);
        //内存缓存区大小
        props.put("buffer.memory", memory);
        //指定消息key序列化方式
        props.put("key.serializer", keySerializer);
        //指定消息本身的序列化方式
        props.put("value.serializer", valueSerializer);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        return producer;
    }
}
```
