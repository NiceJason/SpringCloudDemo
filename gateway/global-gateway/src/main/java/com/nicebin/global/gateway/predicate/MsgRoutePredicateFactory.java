package com.nicebin.global.gateway.predicate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author DiaoJianBin
 * @Description 自定义路由断言, 要求有两个
 * 名字必须是 配置+RoutePredicateFactory  本类的配置字段是name
 * 必须继承 AbstractRoutePredicateFactory <配置类>
 *
 * 这里就是个测试，访问路由/test/testGateway的时候，需要传入appId
 * 这里会检测appId的开头和总体长度
 *
 * @Date 2021/3/26 15:20
 */
@Component
public class MsgRoutePredicateFactory extends AbstractRoutePredicateFactory<MsgRoutePredicateFactory.Config> {

    @Data
    @NoArgsConstructor
    public static class Config {
        //appId开头
        private String appIdBegin;
        //appId长度
        private int length;
    }

    public MsgRoutePredicateFactory() {
        super(MsgRoutePredicateFactory.Config.class);
    }

    /**
     * 读取配置文件的中参数值 给他赋值到配置类中的属性上
     *
     * @return 配置类的数据
     */
    @Override
    public List<String> shortcutFieldOrder() {
        List<String> attributes = new ArrayList<>();
        attributes.add("appIdBegin");
        attributes.add("length");
        return attributes;
    }

    @Override
    public Predicate<ServerWebExchange> apply(MsgRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            boolean check = true;
            String appId = serverWebExchange.getRequest().getQueryParams().getFirst("appId");

            if (config.getLength() != appId.length()) {
                System.out.println("appId长度不对");
                check = false;
            }

            if (!appId.startsWith(config.getAppIdBegin())) {
                System.out.println("appId开头不对 正确开头=" + config.getAppIdBegin());
                check = false;
            }

            //也能读取body里的值，不过要注意，输入流跟filter一样，读一次里面就没值了
            //需要封装一下 https://blog.csdn.net/lz710117239/article/details/80651361
            Flux<DataBuffer>  body = serverWebExchange.getRequest().getBody();

            return check;
        };
    }
}
