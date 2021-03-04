package com.rabbitmqdemo.demo.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author : DiaoJianBin
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@Configuration
public class RabbitTemplateConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory, RabbitTemplateConfigurer configurer){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();

        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);
                System.out.println();
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback(){

            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("ReturnCallback:     "+"消息："+returnedMessage.getMessage());
                System.out.println("ReturnCallback:     "+"回应码："+returnedMessage.getReplyCode());
                System.out.println("ReturnCallback:     "+"回应信息："+returnedMessage.getReplyText());
                System.out.println("ReturnCallback:     "+"交换机："+returnedMessage.getExchange());
                System.out.println("ReturnCallback:     "+"路由键："+returnedMessage.getRoutingKey());
                System.out.println();
            }
        });

        //必须得使用这种绑定，这样才能让RabbitmqConfig里的MessageConver进行绑定
        configurer.configure(rabbitTemplate,connectionFactory);
        return rabbitTemplate;
    }
}
