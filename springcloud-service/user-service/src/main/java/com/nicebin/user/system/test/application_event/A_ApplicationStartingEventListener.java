package com.nicebin.user.system.test.application_event;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author DiaoJianBin
 * @Description 第1事件
 * @Date 2021/6/21 11:16
 */
public class A_ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    private volatile AtomicInteger count = new AtomicInteger(0);
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        String[] info = this.toString().split("\\.");
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，ApplicationStartingEvent事件被"+info[info.length-1]+"接收，执行次数="+ count.incrementAndGet());
    }
}
