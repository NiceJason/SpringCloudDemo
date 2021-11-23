package com.nicebin.user.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.springframework.context.annotation.Primary;
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
        System.out.println(Thread.currentThread().getName()+" 例如在这里添加token");
        template.header("token","123");

        //Seata事务需要在这添加xid
        String xid = RootContext.getXID();
        System.out.println("Seata的xid="+xid);
        if(xid != null){
            template.header(RootContext.KEY_XID,xid);
        }
    }
}
