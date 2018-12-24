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

    @KafkaListener(topics = "${kafka.topic.serverTopic}")
    public void listenTopic (ConsumerRecord<?, ?> record){
        System.out.printf("--------------------- %s --------------------------- %s \n", DateUtils.formatYmdHms(new Date()) , record.offset());
        System.out.printf("topic = %s , offset = %s \n", record.topic(), record.offset());
        System.out.printf("partition = %s\n", record.partition());
        System.out.printf("key = %s , value = %s \n", record.key() , record.value());
        System.out.printf("--------------------- %s --------------------------- %s \n\n", DateUtils.formatYmdHms(new Date()), record.offset());
    }
}
