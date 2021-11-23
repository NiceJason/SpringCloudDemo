package com.nicebin.user.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * redis发布/订阅的处理类
 */
@Slf4j
public class DelegeExpire {

    public void receiveMessage(String message){
        log.info("DelegeExpire获得过期key< " + message + " >");
    }

}
