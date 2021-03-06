package com.example.wangfj.config;

import com.example.wangfj.common.utils.DateUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import java.util.Date;

/**
 * @author wangfujie
 * @version 1.0.0
 * @email wangfj@chinawiserv.com
 */
public class Listener {

    @KafkaListener(topics = "test")
    public void listenTest (ConsumerRecord<?, ?> record) throws Exception {
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