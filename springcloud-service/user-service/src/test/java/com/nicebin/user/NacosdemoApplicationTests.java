package com.nicebin.user;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.nicebin.user.service.UserTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration()
public class NacosdemoApplicationTests {

    @Test
    public void redissonTest() throws Exception{
        System.out.println("1");
    }
}
