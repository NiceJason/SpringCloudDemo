package com.rabbitmqdemo.demo;

import com.rabbitmqdemo.demo.entity.TestInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/2/26 9:32
 */
@Configuration(proxyBeanMethods = false)
public class TestConfig {

    @Bean
    public TestInfo testInfo(){
        return new TestInfo("初始信息");
    }
}
