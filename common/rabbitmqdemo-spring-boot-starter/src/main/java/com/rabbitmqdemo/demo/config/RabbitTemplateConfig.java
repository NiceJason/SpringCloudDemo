package com.rabbitmqdemo.demo.config;

import com.rabbitmqdemo.demo.entity.TestInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author : DiaoJianBin
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@Configuration(proxyBeanMethods = false)
public class RabbitTemplateConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory, RabbitTemplateConfigurer configurer) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();

        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        //消息确认后的回调(确认模式)
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

            System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
            System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
            System.out.println("ConfirmCallback:     " + "原因：" + cause);
            System.out.println();
        });

        //消息未投递到Queue的退回处理（退回模式）
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {

            Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
            TestInfo testInfo = (TestInfo) converter.fromMessage(message);
            System.out.println("ReturnCallback:     " + "消息：" + testInfo.getInfo());
            System.out.println("ReturnCallback:     " + "回应码：" + replyCode);
            System.out.println("ReturnCallback:     " + "回应信息：" + replyText);
            System.out.println("ReturnCallback:     " + "交换机：" + exchange);
            System.out.println("ReturnCallback:     " + "路由键：" + routingKey);
            System.out.println();

        });

        //必须得使用这种绑定，这样才能让RabbitmqConfig里的MessageConver进行绑定
        configurer.configure(rabbitTemplate, connectionFactory);
        return rabbitTemplate;
    }
}
