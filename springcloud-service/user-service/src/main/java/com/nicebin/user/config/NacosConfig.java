package com.nicebin.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/5 15:25
 */
@Configuration(proxyBeanMethods = false)
public class NacosConfig {

}
