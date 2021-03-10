package com.nicebin.user.ribbon.rule;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author DiaoJianBin
 * @Description Ribbon的自定义规则
 *              仿照NacosRule进行书写
 *              如果直接加@Compoment，将会成为user-service里的全局规则
 *              无论请求哪个服务，都会用此规则，这样不好，所以在配置文件中指定
 * @Date 2021/3/10 14:51
 */
public class CustomRuleA extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Autowired
    private NacosServiceManager nacosServiceManager;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        System.out.println("CustomRuleA初始化，这里有各种配置信息可以获得，可以debug此处查看clientConfig里面的信息");
    }

    @SneakyThrows
    @Override
    public Server choose(Object key) {

        //获取服务配置的集群名
        String clusterName = nacosDiscoveryProperties.getClusterName();
        //当前的版本号 配置文件配置的metadata信息
        String currentVersion = nacosDiscoveryProperties.getMetadata().get("version");
        System.out.println("当前服务集群名称="+clusterName+" 版本号="+currentVersion);
        //获得当前服务分组
        String group = nacosDiscoveryProperties.getGroup();
        //获取负载均衡器
        ZoneAwareLoadBalancer baseLoadBalancer = (ZoneAwareLoadBalancer) getLoadBalancer();
        //调用服务的名字
        String invokedServerName = baseLoadBalancer.getName();
        //获取namingServer（包含nacos注册发现相关api）
        NamingService namingService = nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties());
        //获取被调用的服务的所有实例
        List<Instance> invokedAllInstanceList = namingService.getAllInstances(invokedServerName,group);

        //默认获取最后一个
        Instance instance = invokedAllInstanceList.get(invokedAllInstanceList.size()-1);
        System.out.println("CustomRuleA 选择好服务的端口="+instance.getPort());
        return new NacosServer(instance);
    }
}
