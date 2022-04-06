package com.nicebin.dubbo.entity;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Date 2022/3/31 14:27
 */
@Component
public class RocketMQSendCallback implements SendCallback {
    @Override
    public void onSuccess(SendResult sendResult) {
        System.out.println("成功向RockerMQ发送消息，sendResult="+sendResult);
    }

    @Override
    public void onException(Throwable e) {
        System.out.println("向RockerMQ信息发送失败,e="+e);
    }
}
