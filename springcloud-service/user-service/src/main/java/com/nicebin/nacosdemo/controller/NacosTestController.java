package com.nicebin.nacosdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/3 17:15
 */
@RestController
@RefreshScope
public class NacosTestController {

    //系统缓存过期时间允许最小值（秒）
    @Value("${system.cache.min.expire}")
    private long minExpire;

    //系统缓存过期时间允许最大值（秒）
    @Value("${system.cache.max.expire}")
    private long maxExpire;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return minExpire+" "+maxExpire;
    }

}
