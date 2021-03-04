package com.rabbitmqdemo.demo.consumer;

import com.rabbitmqdemo.demo.entity.TestInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消费：直接交换机连着的队列
 */
@Component
public class DirectConsumer {

    @RabbitListener(queues = "directQueue-A")//监听的队列名称 TestDirectQueue
    public void directConsumer1(@Payload TestInfo testInfo, @Headers Map<String,String> map){
        System.out.println("directConsumer1 消费directQueue-A："+testInfo.getInfo()+" ，此消息唯一id ="+map.get("amqp_messageId"));
    }

    @RabbitListener(queues = "directQueue-A")//监听的队列名称 TestDirectQueue
    public void directConsumer2(@Payload TestInfo testInfo, @Headers Map<String,String> map){
        System.out.println("directConsumer2 消费directQueue-A："+testInfo.getInfo()+" ，此消息唯一id ="+map.get("amqp_messageId"));
    }

    @RabbitListener(queues = "directQueue-B")//监听的队列名称 TestDirectQueue
    public void directConsumer3(@Payload TestInfo testInfo,@Headers Map<String,String> map){
        System.out.println("directConsumer3 消费directQueue-B："+testInfo.getInfo()+" ，此消息唯一id ="+map.get("amqp_messageId"));
    }
}
