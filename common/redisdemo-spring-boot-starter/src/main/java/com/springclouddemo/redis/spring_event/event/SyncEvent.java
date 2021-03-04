package com.springclouddemo.redis.spring_event.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author DiaoJianBin
 * @Description 异步事件，监听此事件的类都必须异步处理
 * @Date 2021/3/3 15:55
 */
@Getter
@Setter
public class SyncEvent extends ApplicationEvent {
    String message;
    public SyncEvent(Object source,String message) {
        super(source);
        this.message = message;
    }
}
