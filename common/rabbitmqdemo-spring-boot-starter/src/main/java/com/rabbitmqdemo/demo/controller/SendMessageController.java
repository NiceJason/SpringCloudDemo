package com.rabbitmqdemo.demo.controller;

import com.rabbitmqdemo.demo.entity.TestInfo;
import lombok.Data;
import org.apache.catalina.Session;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * @Author : DiaoJianBin
 * @CreateTime : 2019/9/3
 * @Description : 充当生产者生成信息
 **/
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessageA")
    public String sendDirectMessageA() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, direct!";
        TestInfo testInfo = new TestInfo();
        testInfo.setInfo(messageData);

        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("directExchangeA", "directExchange", testInfo,message -> {
            //设置这条信息的唯一id
            message.getMessageProperties().setMessageId(messageId);
            return message;
        });
        return messageData;
    }


    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, fanout!";
        TestInfo testInfo = new TestInfo();
        testInfo.setInfo(messageData);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("fanoutExchangeA",  null,testInfo,message -> {
            //设置这条信息的唯一id
            message.getMessageProperties().setMessageId(messageId);
            return message;
        });
        return messageData;
    }

    @GetMapping("/sendTopicMessage")
    public String sendTopicMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, topic!";
        TestInfo testInfo = new TestInfo();
        testInfo.setInfo(messageData);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("topicExchangeA", "abcd.topicA.ed.f", testInfo,message -> {
            //设置这条信息的唯一id
            message.getMessageProperties().setMessageId(messageId);
            return message;
        });
        return messageData;
    }


    @GetMapping("/sendAckMessage")
    public String sendAckMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, Ack!";
        TestInfo testInfo = new TestInfo();
        testInfo.setInfo(messageData);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("ackDirectExchangeA", "ackA", testInfo,message -> {
            //设置这条信息的唯一id
            message.getMessageProperties().setMessageId(messageId);
            return message;
        });
        return messageData;
    }

    @GetMapping("/sendDelayedMessage/{delayedTime}")
    public String sendDelayedMessage(@PathVariable(name = "delayedTime") int delayedTime) {

        String messageData = "test message, 延时时间为 "+delayedTime+" 秒";
        TestInfo testInfo = new TestInfo();
        testInfo.setInfo(messageData);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("delayedDirectExchangeA", "delayedA", testInfo,new Processor(delayedTime));
        return messageData;
    }

    /**
     * 延时消息的增强类
     */
    @Data
    class Processor implements MessagePostProcessor {
        //延时时间，单位秒
        int delayedTime;

        public Processor(int delayedTime){
            this.delayedTime = delayedTime;
        }

        @Override
        public Message postProcessMessage(Message message) throws AmqpException {
            message.getMessageProperties().setDelay(delayedTime*1000);
            return  message;
        }
    }

    /**
     * 发送一条错误信息，接受者会发出异常
     * 主要是用来测试消费者异常处理
     * @return
     */
    @GetMapping("/sendErrorMessage")
    public String sendErrorMessage() {

        String messageData = "test message, 接受者会发出异常!";
        TestInfo testInfo = new TestInfo();
        testInfo.setInfo(messageData);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("errorTestExchangeA", "errorTestA", testInfo);
        return messageData;
    }
}
