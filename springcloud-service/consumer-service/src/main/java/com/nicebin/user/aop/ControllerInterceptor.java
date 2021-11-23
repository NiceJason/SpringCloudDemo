package com.nicebin.user.aop;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author DiaoJianBin
 * @Date 2021/9/14 8:43
 */
@Aspect
@Component
@Slf4j
public class ControllerInterceptor {

    @Autowired
    HttpServletRequest request;

    /**
     * 商户端控制层切点
     */
    @Pointcut(value = "@annotation(io.seata.spring.annotation.GlobalTransactional) || @annotation(io.seata.spring.annotation.GlobalLock)")
    public void businessPoint() {

    }

    @SneakyThrows
    @AfterThrowing("businessPoint()")
    public void seataActive() {
        if (RootContext.inGlobalTransaction()) {
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        }
    }
}
