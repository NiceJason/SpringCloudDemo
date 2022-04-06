package com.nicebin.user.consume.rocketmq;

import com.nicebin.api.dubbo.constant.RocketMQTopicConst;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Date 2022/4/1 11:33
 */
@Component
@RocketMQMessageListener(topic = RocketMQTopicConst.orderTopic, consumerGroup = "consumerGroup4")
public class OrderTopicConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("OrderTopicConsumer收到="+message);
    }
}
