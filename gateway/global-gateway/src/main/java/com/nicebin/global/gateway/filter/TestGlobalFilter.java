package com.nicebin.global.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author DiaoJianBin
 * @Description  测试全局网关拦截器
 *
 *               全网网关拦截器若想要生效，以下条件之一需要符合
 *               1.当前网关必须配置其他非全网拦截器（还真的很神奇）
 *               2.全网网关拦截器必须实现Ordered接口
 * @Date 2021/3/28 23:06
 */
@Component
@Slf4j
public class TestGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入TestGlobalFilter 全局网关拦截器");
//        exchange.getSession().map(WebSession::save).then(chain.filter(exchange));

        System.out.println("退出TestGlobalFilter 全局网关拦截器");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
