package com.zhang.rabbitmqproducer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Slf4j
@RestController
public class SendController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public Object send(){
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("key","111");
        map1.put("value","这是-msg.one");
        rabbitTemplate.convertAndSend("topicExchange","msg.one",map1);

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("key","222");
        map2.put("value","这是-msg.two");
        rabbitTemplate.convertAndSend("topicExchange","msg.two",map2);
        return "ok";
    }
}
