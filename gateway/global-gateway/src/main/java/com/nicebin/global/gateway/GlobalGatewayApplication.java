package com.nicebin.global.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/24 16:21
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GlobalGatewayApplication {

    public static void main(String[] args){
        SpringApplication.run(GlobalGatewayApplication.class,args);
    }
}
