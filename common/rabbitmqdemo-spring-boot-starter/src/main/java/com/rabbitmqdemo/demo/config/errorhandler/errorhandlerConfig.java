package com.rabbitmqdemo.demo.config.errorhandler;

import com.rabbitmqdemo.demo.entity.TestInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author DiaoJianBin
 * @Description 自定义错误处理的注册类
 * @Date 2021/2/25 15:05
 */
@Configuration
public class errorhandlerConfig {

    /**
     * 自定义消费者异常处理类，单个方法
     *
     * @return
     */
    @Bean
    public RabbitListenerErrorHandler cunsomErrorHandlerA(){
        return new RabbitListenerErrorHandler() {
            @Override
            public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {
                System.out.println("方法异常的局部处理");
                Throwable throwable = exception.getCause();

                System.out.println("调用者传来的异常为："+throwable.getMessage());

                System.out.println("消息内容为："+((TestInfo)message.getPayload()).getInfo());
                System.out.println("这里可以根据异常做相应的逻辑或者做日志");
                System.out.println("除非方法是手动ACK模式，不然自动触发Spring补偿机制进行重发");
                return exception;
            }
        };
    }
}
