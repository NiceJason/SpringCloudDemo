package com.nicebin.user.system.test.context_event;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，ContextStoppedEvent事件被"+info[info.length-1]+"接收，执行次数="+ count.incrementAndGet());
    }
}
