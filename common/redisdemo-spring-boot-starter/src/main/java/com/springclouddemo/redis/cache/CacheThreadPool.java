package com.springclouddemo.redis.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author NiceBin
 * @description: 缓存线程池，对需要操作缓存的线程进行统一管理
 * @date 2019/11/28 15:25
 */
@Configuration
public class CacheThreadPool {
    private ThreadPoolExecutor threadPoolExecutor;

    @Value("${system.cache.thread.core.pool.size}")
    private int threadCorePoolSize;
    @Value("${system.cache.thread.maximum.pool.size}")
    private int threadMaximumPoolSize;
    @Value("${system.cache.thread.keep.alive.time}")
    private long threadKeepAliveTime;

    public CacheThreadPool(){}

    public CacheThreadPool(ThreadPoolExecutor threadPoolExecutor){
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Bean
    public CacheThreadPool init(){
        threadPoolExecutor = new ThreadPoolExecutor(
                threadCorePoolSize,
                threadMaximumPoolSize,
                threadKeepAliveTime,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(threadMaximumPoolSize,true)
        );
        //允许核心线程也受KeepAliveTime的影响
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return new CacheThreadPool(threadPoolExecutor);
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
}
