package com.koushr.kafka_producer_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KafkaProducerTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerTestApplication.class, args);
    }
}
