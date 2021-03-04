package com.rabbitmqdemo.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author DiaoJianBin
 * @Description 需要发送的测试消息
 * @Date 2021/2/23 15:21
 */
@Data
public class TestInfo implements Serializable {
    private String info;
    private Date data= new Date();

    public TestInfo(){};

    public TestInfo(String info){
        this.info = info;
    };
}
