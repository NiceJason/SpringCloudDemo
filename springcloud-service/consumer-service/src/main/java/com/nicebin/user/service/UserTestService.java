package com.nicebin.user.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/8 10:47
 */
@Service
public class UserTestService {

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
