package com.nicebin.global.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

/**
 * @Author chenqing
 */
@Configuration
public class SessionIdConfig {
    @Bean
    public WebSessionIdResolver webSessionIdResolver() {
        CookieWebSessionIdResolver resolver = new CookieWebSessionIdResolver();
        // 重写定义 cookie 名字
        resolver.setCookieName("YXPT-GATEWAY-SESSIONID");
        return resolver;
    }


}
