package com.springclouddemo.redis.spring_event.listener;

import com.springclouddemo.redis.spring_event.event.SyncEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 测试Spring事件机制的自定义事件监听器
 *              此处采取异步
 * @Date 2021/3/3 15:11
 */
@Component
public class TestEventListenerC implements ApplicationListener<SyncEvent> {
    @Async
    @Override
    public void onApplicationEvent(SyncEvent event) {
        System.out.println("TestEventListenerC的Thread为 "+Thread.currentThread().getName());
        try{
            Thread.sleep(3 * 1000);
        }catch (Exception e){

        }
        System.out.println("3s休眠结束TestEventListenerC 异步监听到 "+event.getMessage());
    }
}
