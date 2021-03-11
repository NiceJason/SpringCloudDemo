package com.springclouddemo.redis.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.springclouddemo.redis.cache.RedisCacheMgr;
import com.springclouddemo.redis.constant.SystemCacheEnum;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author NiceBin
 * @description: CacheManager初始化
 *               目前系统只用一个Manager，使用RedisCacheManager
 *               根据SystemStaticValue中的SystemCache枚举内容进行Cache的注册
 *               配置启动前需要DefaultListableBeanFactory.class先加载完成
 *               不然CacheManager或者Cache想用的时候会报错
 * @date 2019/11/13 17:02
 */
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "system.cache")
@Import(DefaultListableBeanFactory.class)
@Setter
public class CacheConfig {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    ApplicationContext applicationContext;

    //系统缓存过期时间允许最小值（秒）
    private long minExpire;

    //系统缓存过期时间允许最大值（秒）
    private long maxExpire;

    @Bean
    public RedisCacheMgr cacheManager() {

        //创建Json自定义序列化器
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        //包装成SerializationPair类型
        RedisSerializationContext.SerializationPair serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer);

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1))
                .computePrefixWith(cacheName -> "Cache"+cacheName);
        // 针对不同cacheName，设置不同的过期时间，用了双括号初始化方法~
        Map<String, RedisCacheConfiguration> initialCacheConfiguration = new HashMap<String, RedisCacheConfiguration>() {{
            SystemCacheEnum[] systemCaches = SystemCacheEnum.values();
            Arrays.asList(systemCaches).forEach((systemCache)->
                    put(systemCache.getCacheName(),RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(systemCache.getSurviveTime()))
                    .serializeValuesWith(serializationPair)));
        }};
        RedisCacheMgr redisCacheMgr = new RedisCacheMgr(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory),defaultCacheConfig,initialCacheConfiguration,true,applicationContext,minExpire,maxExpire);
        //设置白名单---非常重要********
        /*
        使用fastjson的时候：序列化时将class信息写入，反解析的时候，
        fastjson默认情况下会开启autoType的检查，相当于一个白名单检查，
        如果序列化信息中的类路径不在autoType中，autoType会默认开启
        反解析就会报com.alibaba.fastjson.JSONException: autoType is not support的异常
        */
        ParserConfig.getGlobalInstance().addAccept("com.tophousekeeper");
        return redisCacheMgr;
    }
}
