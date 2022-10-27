package com.koushr.kafka_producer_test.init;

import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Order(0)
public class KafkaConnector implements CommandLineRunner {
    private static final Log log = LogFactory.getLog(KafkaConnector.class);

    @Getter
    public Producer<String, String> producer;

    @Override
    public void run(String... args) {
        log.info("KafkaConnector init start");
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.10.20.187:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("acks", "1");
        props.put("retries", 3);// 指的是send()还是send线程的run实现？
        props.put("retry.backoff.ms", 50);// 默认是100ms

        props.put("buffer.memory", 1024 * 1024 * 1024);// 默认值是32M，改为1G
        props.put("max.block.ms", 180000);// 默认值是1min，改为3min

        props.put("compression.type", "snappy");

        props.put("send.buffer.bytes", 1024000);
        props.put("receive.buffer.bytes", 1024000);

        props.put("client.id", "prd-1");

        props.put("request.timeout.ms", 2000);// 默认是30000ms
        props.put("delivery.timeout.ms", 30000);// 默认是120000ms

        props.put("batch.size", 1024 * 1024);// 默认是16k
        props.put("linger.ms", 1000);//默认是0，改为100ms

        props.put("max.request.size", 1024 * 1024 * 500);//单次最多发送100M数据

        props.put("security.protocol", "PLAINTEXT");//交互协议

        producer = new KafkaProducer<>(props);
        log.info("KafkaConnector init end");
    }
}
