package com.springbootdemo.test.bean_life.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author DiaoJianBin
 * @Description 测试Bean的生命周期
 * @Date 2021/7/2 14:21
 */
@Component
public class LifeCycleBean {

    @Autowired
    LifeCycleBean lifeCycleBean;

    public LifeCycleBean(){
        System.out.println("LifeCycleBean的无参构造方法被执行");
    }

    @PostConstruct
    public void init1(){
        System.out.println("@PostConstruct修饰的初始化执行");
    }
}
