package com.nicebin.user.mybatis.Interceptor;

import com.nicebin.user.service.SeataService;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

/**
 * 测试Mybaits的扩展点
 *
 * @Author DiaoJianBin
 * @Date 2022/2/28 11:18
 */
@Intercepts({@Signature(type = SeataService.class,method = "insertUserAndBusiness",args = {})})
public class MybatisInterceptor implements Interceptor {

    @Autowired
    InterceptorChain interceptorChain;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MybatisInterceptor：sql执行前");
        Object result = invocation.proceed();
        System.out.println(result);
        System.out.println("MybatisInterceptor：sql执行后");
        return result;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MybatisInterceptor：进入plugin方法");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("MybatisInterceptor：进入setProperties方法");
    }
}
