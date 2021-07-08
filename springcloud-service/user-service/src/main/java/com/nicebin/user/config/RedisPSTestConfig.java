package com.nicebin.user.config;

import com.nicebin.user.entity.DelegeExpire;
import com.nicebin.user.entity.ReceiverDeleteListener;
import com.nicebin.user.entity.ReceiverExpireListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author DiaoJianBin
 * @Description redis的发布（publish）/订阅（subscribe）
 * @Date 2021/6/17 17:00
 */
@Configuration
public class RedisPSTestConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        container.addMessageListener(listenerAdapter, new ChannelTopic("__keyevent@0__:expired"));
        container.addMessageListener(new ReceiverExpireListener(),new ChannelTopic("__keyevent@0__:expired"));
        container.addMessageListener(new ReceiverDeleteListener(),new ChannelTopic("__keyevent@0__:del"));

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(){
        return new MessageListenerAdapter(new DelegeExpire(), "receiveMessage");
    }

    /**
     * 专门为redis的ContainerMessageListener使用的线程池，所以Bean的名字一定要叫这个
     * 详情看笔记：SpringBoot项目中创建大量redisMessageListenerContailner-X线程导致内存溢出
     * @return
     */
    @Bean("springSessionRedisTaskExecutor")
    ThreadPoolExecutor threadPoolExecutor(){
        return new ThreadPoolExecutor(5,10,3L, TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(100));
    }
}
