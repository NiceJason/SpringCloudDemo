package com.springclouddemo.redis.spring_event.listener;

import com.springclouddemo.redis.spring_event.event.TestEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 测试Spring事件机制的自定义事件监听器
 *              此处用实现接口的形式
 * @Date 2021/3/3 14:57
 */
@Component
public class TestEventListenerA implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent event){
        System.out.println("TestEventListenerA的Thread为 "+Thread.currentThread().getName());
        System.out.println("TestEventListenerA监听到了 "+event.getMessage());
    }
}
