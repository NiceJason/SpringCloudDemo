package com.rabbitmqdemo.demo.exception;

import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;

/**
 * @Author DiaoJianBin
 * @Description 自定义RabbitMQ的致命异常错误判断
 * @Date 2021/2/25 15:44
 */
public class CustomFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

    /**
     * 只要不是自定义异常，都认为抛出了致命异常
      * @param t
     * @return
     */
    @Override
    public boolean isFatal(Throwable t) {
        System.out.println("判断异常是否致命");
        boolean isFatal  =t.getCause() instanceof CustomException;
        return !(isFatal);
    }
}
