package com.nicebin.user.feign.feign_interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description  openFeign的拦截器，可以在请求发送前做些处理
 * @Date 2021/3/14 16:10
 */
@Component
public class UserFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("例如在这里添加token");
        template.header("token","123");
    }
}
