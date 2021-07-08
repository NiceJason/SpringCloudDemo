package com.springbootdemo.test.bean_life.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description Bean生命周期探索类
 * @Date 2021/7/6 15:36
 */
@Component
public class LifeCycleSmartInstantiationAwareBeanPostProcessor implements BeanFactoryPostProcessor,SmartInstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

    public LifeCycleSmartInstantiationAwareBeanPostProcessor(){
        System.out.println("LifeCycleSmartInstantiationAwareBeanPostProcessor 默认构造函数");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory()方法执行，在Bean生成[之前]，可以对BeanFactory进行操作");
    }

    /**
     * 这个方法需要其他Bean注入lifeCycleBean才会调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {

        if("lifeCycleBean".equals(beanName)){
            System.out.println("getEarlyBeanReference()方法执行，有其他Bean调用了"+beanName+"的引用，可以在这生成代理");
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)){
            System.out.println("postProcessBeforeInstantiation()方法执行，在Bean="+beanName+"实例化[之前]");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)){
            System.out.println("postProcessAfterInstantiation()方法执行，在Bean="+beanName+"实例化[之后]");
        }

        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)){
            System.out.println("postProcessBeforeInitialization()方法执行，在Bean="+beanName+"初始化[之前]");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if("lifeCycleBean".equals(beanName)){
            System.out.println("postProcessAfterInitialization()方法执行，在Bean="+beanName+"初始化[之后]，可以在这生成代理");
            System.out.println();
        }
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)){
            System.out.println("postProcessBeforeDestruction()方法执行，在Bean="+beanName+"自定义销毁方法[之前]");
        }
    }
}
