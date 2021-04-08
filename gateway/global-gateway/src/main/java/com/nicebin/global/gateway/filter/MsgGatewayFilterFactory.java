package com.nicebin.global.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.nicebin.common.exception.SystemException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author DiaoJianBin
 * @Description   这是一个自定义的过滤器工厂类,要求有两个
 *  名字必须是 配置+GatewayFilterFactory  本类的配置字段是 Surprise
 *  必须继承 AbstractGatewayFilterFactory <配置类>
 *
 *               参考 https://blog.csdn.net/forezp/article/details/85057268
 *
 * @Date 2021/3/28 22:17
 */
@Component
public class MsgGatewayFilterFactory extends AbstractGatewayFilterFactory {

    /**
     * Cookie里是否有token
     * 如果没有则拒绝请求
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {

            ServerHttpRequest serverRequest = exchange.getRequest();
            HttpCookie tokenCookie = serverRequest.getCookies().getFirst("token");
            if(tokenCookie ==null || StringUtils.isEmpty(tokenCookie.getValue())){
                System.out.println("MsgGatewayFilterFactory拦截 因为cookie里未找到token");
                //这里也可以return Mono.empty()
                throw new SystemException("cookie里未找到token");
            }
            System.out.println("获取到token = "+tokenCookie.getValue());

            exchange.getSession().flatMap(webSession -> {
                System.out.println("Gateway开始操作WebSession");
                ServerHttpResponse response = exchange.getResponse();
                //这Session只能在网关里用
                //除非这里自己存到缓存里去，像做SpringSession那样，不然分布式不了
                webSession.getAttributes().put("Gateway","Gateway记录了值");
                JSONObject message = new JSONObject();
                message.put("key1","key1的值");
                message.put("key2","key2的值");
                byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                return response.writeWith(Mono.just(buffer));
            }).subscribe();
            return chain.filter(exchange);
        });
    }
}
