package com.nicebin.user.system.test.application_event;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author DiaoJianBin
 * @Description 第4事件
 * @Date 2021/6/21 11:16
 */
public class D_ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    private volatile AtomicInteger count = new AtomicInteger(0);
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        String[] info = this.toString().split("\\.");
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，ApplicationPreparedEvent事件被"+info[info.length-1]+"接收，执行次数="+ count.incrementAndGet());
    }
}
