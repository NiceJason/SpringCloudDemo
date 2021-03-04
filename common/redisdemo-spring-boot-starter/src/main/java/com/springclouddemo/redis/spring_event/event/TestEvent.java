package com.springclouddemo.redis.spring_event.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author DiaoJianBin
 * @Description 测试Spring事件机制的自定义事件
 * @Date 2021/3/3 14:54
 */
@Setter
@Getter
public class TestEvent extends ApplicationEvent {
    private String message;

    public TestEvent(Object source,String message) {
        super(source);
        this.message = message;
    }
}
