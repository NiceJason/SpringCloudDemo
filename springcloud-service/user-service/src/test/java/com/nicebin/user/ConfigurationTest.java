package com.nicebin.user;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/13 11:11
 */
@Configuration
@ConditionalOnClass(com.nicebin.user.InfoTest.class)
public class ConfigurationTest {

    @Bean
    public A a(){
        System.out.println("主动构建A");
        return new A();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public B b(){
        System.out.println("不会主动构建");
        return new B();
    }
}
