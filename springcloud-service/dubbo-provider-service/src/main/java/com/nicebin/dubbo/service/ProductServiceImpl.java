package com.nicebin.dubbo.service;

import com.nicebin.api.dubbo.constant.RocketMQTopicConst;
import com.nicebin.api.dubbo.provider.api.ProductService;
import com.nicebin.dubbo.entity.RocketMQSendCallback;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author DiaoJianBin
 * @Date 2022/3/31 11:04
 */
@DubboService(protocol = "dubbo", version = "1.0.0",validation = "true")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RocketMQSendCallback rocketMQSendCallback;

    @Override
    public void sendTestMessage(String message) {
        //发送不带tag的信息
        rocketMQTemplate.asyncSend(RocketMQTopicConst.testTopic,message,rocketMQSendCallback);

        //发送带消息头的消息，不知道有啥用，试一下
        Map<String,Object> headers = new HashMap<>();
        headers.put("testHeader","你好啊");
        GenericMessage genericMessage = new GenericMessage(message,headers);

        rocketMQTemplate.asyncSend(RocketMQTopicConst.testTopic,genericMessage,rocketMQSendCallback);

    }

    @Override
    public void sendTagMessage(String message) {
        //发送带tag的信息
        rocketMQTemplate.asyncSend(RocketMQTopicConst.testTopic+":"+RocketMQTopicConst.tagOne,message+"-one",rocketMQSendCallback);

        rocketMQTemplate.asyncSend(RocketMQTopicConst.testTopic+":"+RocketMQTopicConst.tagTwo,message+"-two",rocketMQSendCallback);

        rocketMQTemplate.asyncSend(RocketMQTopicConst.testTopic+":"+RocketMQTopicConst.tagThree,message+"-three",rocketMQSendCallback);
    }

    @Override
    public void sendOrderMessage(String message) {
        //顺序发送信息
        rocketMQTemplate.asyncSendOrderly(RocketMQTopicConst.orderTopic,message+"-1","hashKey",rocketMQSendCallback);

        rocketMQTemplate.asyncSendOrderly(RocketMQTopicConst.orderTopic,message+"-2","hashKey",rocketMQSendCallback);

        rocketMQTemplate.asyncSendOrderly(RocketMQTopicConst.orderTopic,message+"-3","hashKey",rocketMQSendCallback);
    }
}
