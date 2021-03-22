package com.nicebin.user.entity;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.naming.NacosNamingMaintainService;
import com.alibaba.nacos.client.naming.NacosNamingService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @Author DiaoJianBin
 * @Description 主要用来测试nocos的注解，利用Nacos与SpringCloud的结合
 *              注意：Springcloud中大多数nacos注解都是不支持的
 * @Date 2021/3/5 8:54
 */
@Component
@ConfigurationProperties(prefix = "annotation.test")
@Data
@RefreshScope
public class AnnotationTestSpringCloud {

    private String mydata;

    private Boolean isTest;

    private int code;

    private List<String> testList;

    //由于大多数注解都无效，所以要使用NacosConfigManager进行操作
    @Autowired
    NacosConfigManager nacosConfigManager;
    //这个有值
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    //这个有值
    @Autowired
    NacosServiceManager nacosServiceManager;

    //这里的注入是为null的
    @NacosInjected
    NacosNamingService nacosNamingService;
    //这里的注入是为null的
    @NacosInjected
    NacosNamingMaintainService nacosNamingMaintainService;

    /**
     * nacosConfigManager随便拿2个常用的需求方法测试一下
     *
     * @throws NacosException
     */
    @PostConstruct
    public void init() throws NacosException {
        ConfigService configService = nacosConfigManager.getConfigService();

        configService.publishConfig("动态发布配置ID","动态发布配置goup","动态发布内容");

        configService.addListener("user-service-dev.properties", "dev", new Listener() {
            //这里正常的写法应该是传个线程池进来，不然每次监听到文件改动，都要重新建立线程，开销大
            @Override
            public Executor getExecutor() {
                return new Executor() {
                    @Override
                    public void execute(Runnable command) {
                        command.run();
                    }
                };
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("监听者获取到信息"+configInfo);
            }
        });
    }

    public String toString(){
//        for (String info :testList){
//            System.out.println(info);
//        }
        return "mydata="+mydata+" isTest="+isTest+" code="+code;
    }


}
