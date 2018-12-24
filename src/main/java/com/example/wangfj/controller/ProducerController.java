package com.example.wangfj.controller;

import com.example.wangfj.common.result.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangfujie
 * @version 1.0.0
 *  生产者Controller
 */
@RestController
@RequestMapping("/kafka")
public class ProducerController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${kafka.topic.serverTopic}")
    private String topicName;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public R sendMessage() {
        try {
            for (int i = 0 ; i < 100 ; i++){
                kafkaTemplate.send(topicName,"key" + i,  "message" + i);
            }
            logger.info("kafka消息发送成功");
            return R.ok("发送kafka成功");
        } catch (Exception e) {
            logger.error("发送kafka失败" , e);
            return R.error("发送kafka失败" );
        }
    }
}
