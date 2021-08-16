package com.rabbitmqdemo.demo;

import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqdemoApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    CachingConnectionFactory connectionFactory;

    @Autowired
    A a;
    @Autowired
    B b;

    @Test
    void contextLoads() {

    }
}
