package com.nicebin.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.concurrent.TimeUnit;

/**
 * @Author DiaoJianBin
 * @Description  sendExpire和
 * @Date 2021/6/17 17:01
 */
@Slf4j
@Controller
@ResponseBody
@RequestMapping("/redisTest")
public class RedisTestController {

    @Autowired
    StringRedisTemplate template;

    /**
     * 发送5秒后会过期的内容
     * @return
     */
    @RequestMapping("/sendExpire")
    public String sendExpire(){

        log.info("Sending message...");
        //如果想往指定channel发送信息，用这个
        //template.convertAndSend("chat", "Hello from Redis!");
        template.opsForValue().set("5s-expire-key","123",5, TimeUnit.SECONDS);

        return "发送完毕";
    }

    /**
     * 删除一个key来触发删除事件
     * @return
     */
    @RequestMapping("/deleteKey")
    public String deleteKey(){
        template.opsForValue().set("need-delete","222");
        template.delete("need-delete");
        return "删除完成";
    }
}
