package com.rabbitmqdemo.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器，看拦截了异常后，自动应答是不是改为成功
 *
 * @Author DiaoJianBin
 * @Date 2021/8/5 14:27
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {

    /**
     * 结果是这里的拦截器是拦截不到@RabbitListener抛出的异常的
     *
     * @param customException
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public String handle(CustomException customException){
        System.out.println("拦截到了异常信息："+customException.getInfo());
        return "全局拦截器处理了异常";
    }
}
