package com.springclouddemo.redis.service;

import com.springclouddemo.redis.constant.SystemCacheEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author DiaoJianBin
 * @Description 用来测试缓存
 * @Date 2021/3/1 15:56
 */
@Service
public class CacheService {

    boolean first = true;

    @Cacheable(value = "testTime",key = "'something'")
    public String getSomething() throws Exception{
        System.out.println("正在获取数据");
        Thread.sleep(2 * 1000);
        System.out.println("成功获取数据");
        if(first){
            first = false;
            return "第一次数据";
        }else{
            return "新数据！！！！";
        }

    }
}
