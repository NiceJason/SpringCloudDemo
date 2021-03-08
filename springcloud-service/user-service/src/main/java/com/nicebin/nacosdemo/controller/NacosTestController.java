package com.nicebin.nacosdemo.controller;

import com.nicebin.nacosdemo.entity.AnnotationTestSpringCloud;
import com.springclouddemo.redis.cache.CacheThreadPool;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/3 17:15
 */
@RestController
@RefreshScope
public class NacosTestController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedissonClient redissonClient;

    //系统缓存过期时间允许最小值（秒）
    @Value("${system.cache.minExpire}")
    private long minExpire;

    //系统缓存过期时间允许最大值（秒）
    @Value("${system.cache.maxExpire}")
    private long maxExpire;

    @Autowired
    private AnnotationTestSpringCloud annotationTestSpringCloud;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return minExpire+" "+maxExpire;
    }

    @GetMapping("/config/getAnnotationTestSpringCloud")
    public String getAnnotationTestSpringCloud() {
        return annotationTestSpringCloud.toString();
    }

    @GetMapping("/sendMessageToBlank")
    public String sendMessageToBlank(){
       return  restTemplate.getForObject("http://blank-service/getMessage/userMessage",String.class);
    }

    @GetMapping("/testRedis")
    public String testRedis(HttpServletRequest request){
        //测试redis
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("user-service","redis-test");

        //测试SpringSession
        HttpSession httpSession = request.getSession();

        //测试Redission
        RLock lock = redissonClient.getLock("user-service-lock");
        lock.lock();

        //测试CacheThreadPool是否初始化成功
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        CacheThreadPool cacheThreadPool = applicationContext.getBean(CacheThreadPool.class);
        System.out.println("缓存线程池大小为 = "+cacheThreadPool.getThreadPoolExecutor().getPoolSize());
        return "ok";
    }
}
