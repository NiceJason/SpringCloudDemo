package com.springclouddemo.redis.cache;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author NiceBin
 * @description: 缓存线程池，对需要操作缓存的线程进行统一管理
 * @date 2019/11/28 15:25
 */
@Component
@ConfigurationProperties(prefix = "system.cache.thread")
@Setter
public class CacheThreadPool {
    private ThreadPoolExecutor threadPoolExecutor;

    private int corePoolSize;

    private int maximumPoolSize;

    private long keepAliveTime;

    public CacheThreadPool(){}

    public CacheThreadPool(ThreadPoolExecutor threadPoolExecutor){
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @PostConstruct
    public void init(){
        threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(maximumPoolSize,true)
        );
        //允许核心线程也受KeepAliveTime的影响
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        this.setThreadPoolExecutor(threadPoolExecutor);
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
}
