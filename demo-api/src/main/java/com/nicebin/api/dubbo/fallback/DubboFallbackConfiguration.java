package com.nicebin.api.dubbo.fallback;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author DiaoJianBin
 * @Date 2021/11/29 14:50
 */
@Configuration
public class DubboFallbackConfiguration {

    @Bean
    public ProviderDubboFallback ProviderDubboFallbackInit(){
        System.out.println("加载ProviderDubboFallback");
        ProviderDubboFallback providerDubboFallback = new ProviderDubboFallback();
        DubboAdapterGlobalConfig.setProviderFallback(providerDubboFallback);

        return providerDubboFallback;
    }

    @Bean
    public ConsumerDubboFallback ConsumerDubboFallbackInit(){
        System.out.println("加载ConsumerDubboFallback");
        ConsumerDubboFallback consumerDubboFallback = new ConsumerDubboFallback();
        DubboAdapterGlobalConfig.setConsumerFallback(consumerDubboFallback);

        return consumerDubboFallback;
    }
}
