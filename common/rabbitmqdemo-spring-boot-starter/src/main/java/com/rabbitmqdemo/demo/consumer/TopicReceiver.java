package com.rabbitmqdemo.demo.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmqdemo.demo.entity.TestInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @auther: NiceBin
 * @description:
 * @date: 2021/2/24 21:46
 */
@Component
public class TopicReceiver implements ChannelAwareMessageListener {

    /**
     * 直接拒绝，用来测试死信队列的重试次数
     * @param message
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("topicAConsumer 拒绝此消息 id="+message.getMessageProperties().getMessageId());
        channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
    }
}
