package com.example.wangfj.controller;

import com.alibaba.fastjson.JSON;
import com.example.wangfj.common.result.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * @author wangfujie
 * @version 1.0.0
 *  生产者Controller
 */
@RestController
@RequestMapping("/kafka")
public class ProducerController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${kafka.consumer.topic}")
    private String topic;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public R sendMessage(@RequestParam("message") String message) {
        try {
            kafkaTemplate.send(topic, "key", message);
            logger.info("发送kafka[test]成功");
            logger.info("kafka[test]的消息={}", message);
            return R.ok("发送kafka[test]成功：" + message);
        } catch (Exception e) {
            logger.error("发送kafka[test]失败：" + message, e);
            return R.error("发送kafka[test]失败：" + message);
        }
    }

    @RequestMapping(value = "/sendList", method = RequestMethod.GET)
    public R sendList() {
        try {
            Map<String, Object> map = new HashMap<>(1);
            List<String> testList = new ArrayList<>();
            testList.add("11111111111111");
            testList.add("2222222222222");
            testList.add("33333333333");
            testList.add("44444444444");
            testList.add("555555555555");
            map.put("testList",testList);
            kafkaTemplate.send("topic_1", "key", JSON.toJSONString(map));
            logger.info("发送kafka[topic_1]成功");
            logger.info("kafka[topic_1]的消息={}", JSON.toJSONString(map) );
            return R.ok("发送kafka[topic_1]成功：" + JSON.toJSONString(map));
        } catch (Exception e) {
            logger.error("发送kafka[topic_1]失败：" + e);
            return R.error("发送kafka[topic_1]失败：");
        }
    }
}
