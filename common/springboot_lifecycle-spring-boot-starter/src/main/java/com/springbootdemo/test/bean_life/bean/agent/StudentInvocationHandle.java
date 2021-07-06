package com.springbootdemo.test.bean_life.bean.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author DiaoJianBin
 * @Description 学生的代理类
 * @Date 2021/7/6 16:03
 */
public class StudentInvocationHandle implements InvocationHandler {

    private Object target;

    public StudentInvocationHandle(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(target, args);
        if("study".equals(method.getName())){
            System.out.println("StudentInvocationHandle 业务执行后的代理输出");
        }

        return obj;
    }
}
