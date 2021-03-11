package com.rabbitmqdemo.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author DiaoJianBin
 * @Description 对RabbitMQ进行一些配置
 * @Date 2021/2/23 15:50
 */
@Configuration(proxyBeanMethods = false)
public class RabbitmqConfig {

    /**
     * {@link org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration}
     * 单纯在RabbitTemplate中设置Jackson2JsonMessageConverter是不够的
     * 因为接收消息的时候会报错
     * 而且这里设置了之后，其他地方不能再另外设置
     * @param objectMapper json序列化实现类
     * @return mq 消息序列化工具
     */
    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
