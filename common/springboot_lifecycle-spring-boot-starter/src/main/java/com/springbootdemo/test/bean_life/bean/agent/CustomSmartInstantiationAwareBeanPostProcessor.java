package com.springbootdemo.test.bean_life.bean.agent;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 自定义代理处理类
 * @Date 2021/7/6 15:36
 */
@Component
public class CustomSmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    //学生已经被代理了嘛，因为代理只需要执行一次
    private boolean isStudentAgent = false;

    /**
     * 这个方法需要其他Bean注入Student才会调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {

        Object obj = bean;
        if("student".equals(beanName)){
            obj = studentAgent(bean,beanName);
        }
        return obj;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object obj = bean;
        if("student".equals(beanName)){
            obj = studentAgent(bean,beanName);
        }
        return obj;
    }

    private Object studentAgent(Object bean, String beanName){
        if ("student".equals(beanName)) {
            if(isStudentAgent){
                System.out.println("CustomSmartInstantiationAwareBeanPostProcessor 学生类已经代理过啦[已被其他Bean调用了引用]");
            }else{
                System.out.println("CustomSmartInstantiationAwareBeanPostProcessor 生成学生代理");
                StudentInvocationHandle studentInvocationHandle = new StudentInvocationHandle(bean);
                isStudentAgent = true;
                return studentInvocationHandle.getProxy();
            }
        }
        return bean;
    }
}
