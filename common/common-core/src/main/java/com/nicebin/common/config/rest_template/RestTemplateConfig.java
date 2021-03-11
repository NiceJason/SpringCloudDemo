package com.nicebin.common.config.rest_template;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/11 15:18
 */
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfig {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        return new RestTemplate(factory);
    }
}
