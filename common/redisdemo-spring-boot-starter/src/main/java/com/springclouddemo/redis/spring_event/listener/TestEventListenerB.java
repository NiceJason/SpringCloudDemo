package com.springclouddemo.redis.spring_event.listener;

import com.springclouddemo.redis.spring_event.event.TestEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 测试Spring事件机制的自定义事件监听器
 *              此处用注解的形式
 * @Date 2021/3/3 15:03
 */
@Component
public class TestEventListenerB {

    @EventListener(classes = {TestEvent.class})
    public void dealEvent(TestEvent testEvent){
        System.out.println("TestEventListenerB的Thread为 "+Thread.currentThread().getName());
        System.out.println("TestEventListenerB监听到 "+testEvent.getMessage());
    }
}
