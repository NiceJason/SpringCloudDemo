package com.rabbitmqdemo.demo.consumer;

import com.rabbitmqdemo.demo.entity.TestInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 延时消息消费者
 * @Date 2021/2/25 11:37
 */
@Component
public class DelayedConsumer {

    @RabbitListener(queues = "delayedQueue-A")
    public void delayedConsumerA(@Payload TestInfo testInfo){
        System.out.println("延时队列消耗信息 "+testInfo.getInfo());
    }
}
