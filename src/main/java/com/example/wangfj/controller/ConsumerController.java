package com.example.wangfj.controller;

import com.example.wangfj.common.utils.DateUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wangfujie
 * @version 1.0.0
 * 消费者Controller
 */
@Component
public class ConsumerController {

    @KafkaListener(topics = "test")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("--------------------- %s --------------------------- \n", DateUtils.formatYmdHms(new Date()));
        System.out.printf("topic = %s, offset = %s \n", record.topic(), record.key());
        System.out.printf("value = %s \n", record.value());
        System.out.printf("--------------------- %s --------------------------- \n\n", DateUtils.formatYmdHms(new Date()));
    }

    @KafkaListener(topics = "topic_1")
    public void listenTopic1 (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("--------------------- %s --------------------------- \n", DateUtils.formatYmdHms(new Date()));
        System.out.printf("topic = %s, offset = %s \n", record.topic(), record.key());
        System.out.printf("value = %s \n", record.value());
        System.out.printf("--------------------- %s --------------------------- \n\n", DateUtils.formatYmdHms(new Date()));
    }
}
