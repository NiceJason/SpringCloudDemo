package com.nicebin.user.consume.rocketmq;

import com.rabbitmq.client.DefaultConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义创建Consumer，因为用注解 @RocketMQMessageListener 无法手动ack
 *
 * @Author DiaoJianBin
 * @Date 2022/4/2 16:43
 */
@Configuration(proxyBeanMethods = false)
public class RocketMQCustomConsumerConfig {

    @Bean
    public DefaultMQPushConsumer rocketMQCustomConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup("CustomConsumer");
        return consumer;
    }
}
