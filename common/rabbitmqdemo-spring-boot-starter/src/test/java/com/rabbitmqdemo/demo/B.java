package com.rabbitmqdemo.demo;

import com.rabbitmqdemo.demo.entity.TestInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/2/26 9:32
 */
@Data
@Component
public class B {
    @Autowired
    TestInfo testInfo;

    public void changeInfo(String info){
        testInfo.setInfo(info);
    }
}
