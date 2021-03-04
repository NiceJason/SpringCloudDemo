package com.springclouddemo.redis.spring_event.listener;

import com.springclouddemo.redis.spring_event.event.SyncEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 测试Spring事件机制的自定义事件监听器
 *              此处采取异步
 * @Date 2021/3/3 15:11
 */
@Component
public class TestEventListenerD {
    @Async
    @EventListener
    public void dealEvent(SyncEvent event) {
        System.out.println("TestEventListenerD的Thread为 "+Thread.currentThread().getName());
        try{
            Thread.sleep(5 * 1000);
        }catch (Exception e){

        }
        System.out.println("5s休眠结束TestEventListenerD 异步监听到 "+event.getMessage());
        throw new RuntimeException("TestEventListenerD抛出异常");
    }
}
