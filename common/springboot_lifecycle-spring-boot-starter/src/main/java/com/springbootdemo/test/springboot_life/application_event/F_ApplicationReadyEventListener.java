package com.springbootdemo.test.springboot_life.application_event;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author DiaoJianBin
 * @Description 第6事件
 * @Date 2021/6/21 11:16
 */
public class F_ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {
    private volatile AtomicInteger count = new AtomicInteger(0);
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String[] info = this.toString().split("\\.");
        String entityNum = info[info.length-1].split("@")[1];
        String print = String.format("ApplicationReadyEvent事件被类@%s接收，执行次数=%s，%s线程执行",entityNum,count.incrementAndGet(),Thread.currentThread().getName());
        System.out.println(print);

        System.out.println("容器已经running，当前容器id="+applicationContext.getId());
    }

    //看看该类所属的ApplicationContext容器是哪个
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
