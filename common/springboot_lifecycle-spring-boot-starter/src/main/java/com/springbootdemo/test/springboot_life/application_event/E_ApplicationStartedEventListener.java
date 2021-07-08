package com.springbootdemo.test.springboot_life.application_event;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author DiaoJianBin
 * @Description 第5事件
 * @Date 2021/6/21 11:16
 */
public class E_ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    private volatile AtomicInteger count = new AtomicInteger(0);
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        String[] info = this.toString().split("\\.");
        String entityNum = info[info.length-1].split("@")[1];
        String print = String.format("ApplicationStartedEvent事件被类@%s接收，执行次数=%s，%s线程执行",entityNum,count.incrementAndGet(),Thread.currentThread().getName());
        System.out.println(print);
    }
}
