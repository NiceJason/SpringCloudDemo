package com.springbootdemo.test.springboot_life.run_listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/6/21 22:07
 */
public class TestSpringApplicationRunListener implements SpringApplicationRunListener {
    private final SpringApplication application;
    private final String[] args;

    public TestSpringApplicationRunListener(SpringApplication sa, String[] args) {
        this.application = sa;
        this.args = args;
    }

    @Override
    public void starting() {
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，自定义starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，自定义environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，自定义contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，自定义contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，自定义started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，自定义running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，自定义finished");
    }
}
