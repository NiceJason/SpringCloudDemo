package com.rabbitmqdemo.demo.consumer;

import com.rabbitmqdemo.demo.entity.TestInfo;
import com.rabbitmqdemo.demo.exception.CustomException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 异常消费者，主要会消费的过程抛出异常
 *              用来测试消费者异常
 * @Date 2021/2/25 15:00
 */
@Component
public class ErrorConsumer {

    /**
     * 预期是先进入cunsomErrorHandlerA，局部异常处理
     * 由于不是致命异常，无法进入全局消费者异常处理
     *
     * @param testInfo
     * @throws Exception
     */
    @RabbitListener(queues = "errorTestQueue-A",errorHandler = "cunsomErrorHandlerA")
    public void errorConsumerA(@Payload TestInfo testInfo) throws Exception{
        System.out.println("errorConsumerA 接收到信息 "+testInfo.getInfo());
        throw new CustomException("01","errorConsumerA 逻辑出错");
    }

}
