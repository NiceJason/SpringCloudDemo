package com.nicebin.user.feign.feign_fallback_factory;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.nicebin.user.feign.feign_fallback.BusinessServiceTestClientFallback;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description factory一般都是处理自身服务发送请求时出现的异常
 *              对于第三方抛出异常，这里一般只能接收到500的错误，里面的错误信息要靠对面自己设置才有的
 *              所以在内部的微服务里，异常应该由全局捕捉处理，封装成ResultJson
 *              通过ResultJson的状态码和信息去判断调用时出现了什么问题
 * @Date 2021/3/14 9:20
 */
@Component
@Slf4j
public class BusinessServiceTestFallballFactory implements FallbackFactory<BusinessServiceTestClientFallback> {
    @Override
    public BusinessServiceTestClientFallback create(Throwable throwable) {
        log.error("business-service调用失败",throwable);
        if (throwable instanceof FlowException) {
            System.out.println("流控规则被触发......");
        } else if (throwable instanceof DegradeException) {
            System.out.println("降级规则被触发...");
        } else if (throwable instanceof AuthorityException) {
            System.out.println("授权规则被触发...");
        } else if (throwable instanceof ParamFlowException) {
            System.out.println("热点规则被触发...");
        } else if (throwable instanceof SystemBlockException) {
            System.out.println("系统规则被触发...");
        }

        return new BusinessServiceTestClientFallback();
    }
}
