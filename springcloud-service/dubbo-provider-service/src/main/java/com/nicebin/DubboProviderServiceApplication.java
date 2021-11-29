package com.nicebin;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.nicebin.*")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.nicebin.api.*")
@ServletComponentScan(basePackages = "com.nicebin.*")
public class DubboProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderServiceApplication.class, args);
    }

}
