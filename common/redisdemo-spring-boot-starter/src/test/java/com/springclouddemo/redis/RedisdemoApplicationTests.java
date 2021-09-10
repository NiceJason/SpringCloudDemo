package com.springclouddemo.redis;

import com.springclouddemo.redis.service.CacheService;
import com.springclouddemo.redis.spring_event.event.SyncEvent;
import com.springclouddemo.redis.spring_event.event.TestEvent;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration()
public class RedisdemoApplicationTests {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    CacheService cacheService;
//    @Autowired
//    RedissonClient redissonClient;

    @Test
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

    @Test
    public void CacheTest() throws  Exception{
        System.out.println(cacheService.getSomething());
        System.out.println(cacheService.getSomething());
        System.out.println(cacheService.getSomething());
        Thread.sleep(13 * 1000);
        for(int i = 0 ;i<10;i++){
            System.out.println(cacheService.getSomething());
        }
        Thread.sleep(10* 1000);
        for(int i = 0 ;i<100;i++){
            System.out.println(cacheService.getSomething());
        }

        RedisCacheManager aa;
    }

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Test
    @SneakyThrows
    public void EventTest(){
        HttpServletRequest request = null;
        ServletInputStream inputStream = request.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] b = new byte[1024];
        StringBuilder builder = new StringBuilder();
        while (bufferedInputStream.available()!=-1){
            bufferedInputStream.read(b);
            builder.append(b.toString());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        reader.readLine();


    }
}
