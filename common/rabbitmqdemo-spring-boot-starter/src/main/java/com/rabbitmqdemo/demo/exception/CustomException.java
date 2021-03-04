package com.rabbitmqdemo.demo.exception;

import lombok.Data;

/**
 * @Author DiaoJianBin
 * @Description 系统的自定义异常
 * @Date 2021/2/25 15:42
 */
@Data
public class CustomException extends RuntimeException{
    //错误代码
    String code;
    //错误信息
    String info;

    public CustomException(String code,String info){
        this.code = code;
        this.info = info;
    }

    public String getMessage(){
        return info;
    }
}
