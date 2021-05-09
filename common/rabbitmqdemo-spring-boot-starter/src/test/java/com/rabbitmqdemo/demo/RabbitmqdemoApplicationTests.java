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
//        RabbitAdmin rabbitAdmin1 = new RabbitAdmin(rabbitTemplate);
//        RabbitAdmin rabbitAdmin2 = new RabbitAdmin(connectionFactory);
//
//        Queue newQueue = new Queue("新队列");
//        rabbitAdmin1.declareQueue(newQueue);
//
//        TopicExchange newTopicExchange = new TopicExchange("新交换机");
//        rabbitAdmin1.declareExchange(newTopicExchange);
//
//        Binding newBinding = BindingBuilder.bind(newQueue).to(newTopicExchange).with("newQueue");
//        rabbitAdmin1.declareBinding(newBinding);
//
//        Message message = null;
//
//        Channel channel = null;
        RabbitTemplateConfigurer aa=null;
        MessageProperties messageProperties = new MessageProperties();

    }
}
