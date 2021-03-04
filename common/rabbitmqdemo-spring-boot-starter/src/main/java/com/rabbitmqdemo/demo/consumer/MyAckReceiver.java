package com.rabbitmqdemo.demo.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmqdemo.demo.entity.TestInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ack手动测试，逻辑为：处理1个，拒绝一个
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {

    boolean lock = false;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {

            Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
            TestInfo testInfo = (TestInfo) converter.fromMessage(message);
            System.out.println("ack已经收到消息");
            if (lock){
                channel.basicReject(deliveryTag,false);//第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
                lock = false;
                System.out.println("ack拒绝处理消息");
            }else{
                Thread.sleep(TimeUnit.SECONDS.toSeconds(5 *1000));
                System.out.println("ack处理消息 "+testInfo.getInfo());
                channel.basicAck(deliveryTag, false); //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
                lock = true;
            }
            System.out.println();
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }
}
