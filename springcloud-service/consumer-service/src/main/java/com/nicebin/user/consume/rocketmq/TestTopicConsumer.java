package com.nicebin.user.consume.rocketmq;

import com.nicebin.api.dubbo.constant.RocketMQTopicConst;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消息如果是在Test的topic里面，尽管消息可能有Tag，也一样会被它消费
 *
 *
 * @Author DiaoJianBin
 * @Date 2022/4/1 11:33
 */
@Component
@RocketMQMessageListener(topic = RocketMQTopicConst.testTopic, consumerGroup = "consumerGroup2")
public class TestTopicConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("TestTopicConsumer收到="+message);
    }
}
