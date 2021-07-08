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

        System.out.println("\nStudentInvocationHandle 执行[目标方法执行前]的代理逻辑");
        Object obj = method.invoke(target, args);
        System.out.println("StudentInvocationHandle 执行[目标方法执行后]的代理逻辑\n");

        return obj;
    }
}
