package com.nicebin.user;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/13 11:11
 */
@Configuration
@ConditionalOnBean(com.nicebin.user.InfoTest.class)
@ConditionalOnClass
public class ConfigurationTest {

    @Bean
    public A a(){
        return new A();
    }
}
