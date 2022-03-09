package com.zhang.rabbitmqconsumer.mq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Slf4j
@Component
public class MQListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("queueOne"),
            exchange = @Exchange(value = "topicExchange",type = ExchangeTypes.TOPIC,durable = "false"),
            key = "msg.one"))
    public void listener11(Map<String,String> map, Message message, Channel channel) {
        log.info("监听queueOne队列，获取到消息：{}",map);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("queueCC"),
            exchange = @Exchange(value = "topicExchange",type = ExchangeTypes.TOPIC,durable = "false"),
            key = "msg.#"))
    public void listener12(Map<String,String> map, Message message, Channel channel) {
        log.info("监听queueTwo队列，获取到消息：{}",map);
    }
}
