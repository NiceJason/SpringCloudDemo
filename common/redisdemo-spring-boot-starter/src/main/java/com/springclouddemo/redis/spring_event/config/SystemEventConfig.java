package com.springclouddemo.redis.spring_event.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @Author DiaoJianBin
 * @Description 对事件异步监听的配置
 * @Date 2021/3/3 15:23
 */
@Configuration(proxyBeanMethods = false)
public class SystemEventConfig implements AsyncConfigurer {

    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(50); //线程池活跃的线程数
        pool.setMaxPoolSize(100); //线程池最大活跃的线程数
        pool.setWaitForTasksToCompleteOnShutdown(true);
        pool.setThreadNamePrefix("lalala");
        pool.initialize();
        return pool;
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return new AsyncUncaughtExceptionHandler() {
            /**
             * @param ex 监听类抛出的异常
             * @param method 为抛出异常的监听类的方法
             * @param params 里面能获得监听类收到的event
             */
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                System.out.println("异步执行收到异常 "+ex.getMessage());
            }
        };
    }
}
