package com.nicebin.user.system.test.application_event;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author DiaoJianBin
 * @Description 第7事件
 * @Date 2021/6/21 11:16
 */
public class G_ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    private volatile AtomicInteger count = new AtomicInteger(0);
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        String[] info = this.toString().split("\\.");
        System.out.println("执行线程="+Thread.currentThread().getName()+" ，ApplicationFailedEvent事件被"+info[info.length-1]+"接收，执行次数="+ count.incrementAndGet());
    }
}
