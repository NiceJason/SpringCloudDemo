package com.rabbitmqdemo.demo.consumer;

import com.rabbitmqdemo.demo.entity.TestInfo;
import lombok.Data;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther: NiceBin
 * @description: 死信队列消费者
 * @date: 2021/2/24 20:04
 */
@Component
public class DeadConsumer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 记录失败次数，大于3则放弃
     * 小于3则原路返回
     * 默认进死信队列都是失败1次
     * @param testInfo
     * @param headers
     * @param message
     */
    @RabbitListener(queues = "sunspring.dlx.queue")
    public void deadConsumer(@Payload TestInfo testInfo, @Headers Map headers, Message message){
        System.out.println("处理死信队列里的消息 "+testInfo.getInfo());
        int retryCount = 1;
        MessageProperties properties = message.getMessageProperties();

        Object retryStr = headers.get("retryCount");
        if(retryStr!=null){
            retryCount = (Integer) retryStr;
        }

        if (headers != null) {
            if (headers.containsKey("x-death")) {
                List<Map<String, Object>> deaths = (List<Map<String, Object>>) headers.get("x-death");
                if (deaths.size() > 0) {
                    Map<String, Object> death = deaths.get(0);
                    String uId = properties.getMessageId();
                    System.out.println("消息id="+uId);
                    if(retryCount>3){
                        System.out.println("重试次数已大于3次，丢弃");
                    }else {
                        System.out.println("当前失败次数 "+retryCount);
                        String exchange = (String)death.get("exchange");
                        String routingKey = (String) ((ArrayList)death.get("routing-keys")).get(0);
                        System.out.println("上次交互机 "+exchange);
                        System.out.println("上次路由key "+routingKey);

                        //重新发送回原来消息位置，这里用topicQueueA测试
                        rabbitTemplate.convertAndSend(exchange,routingKey,testInfo,new Processor(uId,retryCount+1));
                    }
                }
            }
        }
    }

    /**
     * 死信消息的增强类
     */
    @Data
    class Processor implements MessagePostProcessor{
        //全局消息唯一id
        String uId;
        //失败重试次数
        int retryCount;
        public Processor(String uId,int retryCount){
            this.uId = uId;
            this.retryCount =retryCount;
        }

        @Override
        public Message postProcessMessage(Message message) throws AmqpException {
            Map header = message.getMessageProperties().getHeaders();
            message.getMessageProperties().setMessageId(uId);
            header.put("retryCount",retryCount);
            return  message;
        }
    }
}
