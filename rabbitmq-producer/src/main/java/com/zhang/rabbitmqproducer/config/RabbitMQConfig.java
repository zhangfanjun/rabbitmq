package com.zhang.rabbitmqproducer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Configuration
public class RabbitMQConfig {
    /**
     * durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
     * exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
     * autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
     * return new Queue("TestDirectQueue",true,true,false);
     * 一般设置一下队列的持久化就好,其余两个就是默认false
     * <p>
     * 队列名：TestDirectQueue
     *
     * @author zfj
     * @date 2022/3/9
     */
    @Bean
    public Queue queueOne() {
        return new Queue("queueOne", true);
    }

    @Bean
    public Queue queueTwo() {
        return new Queue("queueTwo", true, true, false);
    }

    /**
     * Direct交换机 起名：exchangeOne
     *
     * @author zfj
     * @date 2022/3/9
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange", false, false);
    }

    /**
     * 定义一个topic交换机
     * @author zfj
     * @date 2022/3/9
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange", false, false);
    }

    /**
     * 绑定  将队列和交换机绑定, 并设置用于匹配键：msg.one
     * 为了防止消息丢失，由生产者创建并绑定队列
     * @author zfj
     * @date 2022/3/9
     */
    @Bean
    Binding bindingExchange1() {
        return BindingBuilder.bind(queueOne()).to(topicExchange()).with("msg.one");
    }

    /**
     * 采用通配符，
     * @author zfj
     * @date 2022/3/9
     */
    @Bean
    Binding bindingExchange2() {
//        return BindingBuilder.bind(queueTwo()).to(topicExchange()).with("msg.#").noargs();
        return BindingBuilder.bind(queueTwo()).to(topicExchange()).with("msg.#");
    }
}
