package com.springbootdemo.test.springboot_life.context_event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/6/23 9:33
 */
public class C_ContextStoppedEventListener implements ApplicationListener<ContextStoppedEvent> {
    private volatile AtomicInteger count = new AtomicInteger(0);
    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        String[] info = this.toString().split("\\.");
        String entityNum = info[info.length-1].split("@")[1];
        String print = String.format("！！ContextStoppedEvent事件被类@%s接收，执行次数=%s，%s线程执行！！",entityNum,count.incrementAndGet(),Thread.currentThread().getName());
        System.out.println(print);
    }
}
