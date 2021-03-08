package com.nicebin.nacosdemo;

import com.nicebin.nacosdemo.service.UserTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheManager;
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

    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    UserTestService userTestService;
    @Autowired
    RedissonClient redissonClient;

    @org.junit.Test
    public void redisTest() throws Exception{
        RedisOperations aa = null;
        RedisConnection bb =null;
        //往redis插入5种类型的数据
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("SpringCloud:Redis.demo:Test:StringKey.1","值1");
        valueOperations.set("SpringCloud:Redis.demo:Test:StringKey.2",new InfoTest());
        valueOperations.set("SpringCloud:Redis.part-1:Test:StringKey.2",new InfoTest());
        redisTemplate.expire("StringKey1",10, TimeUnit.SECONDS);
        System.out.println(valueOperations.get("StringKey2"));

        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush("key3","leftpush1");
        listOperations.leftPush("key3","leftpush2");
        listOperations.set("key3",0,"值0");
        System.out.println(listOperations.leftPop("key3"));

        SetOperations setOperations =redisTemplate.opsForSet();
        setOperations.add("setA","a");
        setOperations.add("setB","b");
        setOperations.add("setC","c");
        setOperations.add("setD","d");

        Set keys = redisTemplate.keys("set*");

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        HashOperations hashOperations = redisTemplate.opsForHash();
        Thread.sleep(TimeUnit.SECONDS.toSeconds(5000));
        System.out.println(redisTemplate.getExpire("StringKey1"));

    }

    @org.junit.Test
    public void CacheTest() throws  Exception{
        System.out.println(userTestService.getSomething());
        System.out.println(userTestService.getSomething());
        System.out.println(userTestService.getSomething());
        Thread.sleep(13 * 1000);
        for(int i = 0 ;i<10;i++){
            System.out.println(userTestService.getSomething());
        }
        Thread.sleep(10* 1000);
        for(int i = 0 ;i<100;i++){
            System.out.println(userTestService.getSomething());
        }

        RedisCacheManager aa;
    }

    @Test
    public void redissonTest() throws Exception{
        RLock rLock = redissonClient.getLock("mylock");
        rLock.lock();
        System.out.println("加锁了，开始处理业务");
        Thread.sleep(30*10000);
        System.out.println("业务处理完毕");
        rLock.unlock();
    }
}
