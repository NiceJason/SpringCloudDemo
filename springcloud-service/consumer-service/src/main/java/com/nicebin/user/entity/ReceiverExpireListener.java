package com.nicebin.user.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * redis发布/订阅的处理类，监听key过期事件
 */
@Slf4j
public class ReceiverExpireListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("ReceiverExpireListener接收到过期key={},pattern={},channel={}"
                ,new String(message.getBody()),new String(pattern),new String(message.getChannel()));
    }
}
