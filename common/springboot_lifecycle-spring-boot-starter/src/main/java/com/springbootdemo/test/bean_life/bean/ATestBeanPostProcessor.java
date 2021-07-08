package com.springbootdemo.test.bean_life.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description BeanPostProcessor能否被其他BeanPostProcessor处理呢
 * 答案是不会的，除了对BeanFactory能有影响
 * @Date 2021/7/6 15:36
 */
@Component
public class ATestBeanPostProcessor implements BeanFactoryPostProcessor,SmartInstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor, ApplicationContextAware {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("TestBeanPostProcessor的postProcessBeanFactory()方法执行，在Bean生成[之前]，可以对BeanFactory进行操作");
    }

    /**
     * 这个方法需要其他Bean注入lifeCycleSmartInstantiationAwareBeanPostProcessor才会调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {

        if("lifeCycleSmartInstantiationAwareBeanPostProcessor".equals(beanName)){
            System.out.println("TestBeanPostProcessor的getEarlyBeanReference()方法执行，有其他Bean调用了"+beanName+"的引用，可以在这生成代理");
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if("lifeCycleSmartInstantiationAwareBeanPostProcessor".equals(beanName)){
            System.out.println("TestBeanPostProcessor的postProcessBeforeInstantiation()方法执行，在Bean="+beanName+"实例化[之前]");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if("lifeCycleSmartInstantiationAwareBeanPostProcessor".equals(beanName)){
            System.out.println("TestBeanPostProcessor的postProcessAfterInstantiation()方法执行，在Bean="+beanName+"实例化[之后]");
        }

        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("lifeCycleSmartInstantiationAwareBeanPostProcessor".equals(beanName)){
            System.out.println("TestBeanPostProcessor的postProcessBeforeInitialization()方法执行，在Bean="+beanName+"初始化[之前]");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if("lifeCycleSmartInstantiationAwareBeanPostProcessor".equals(beanName)){
            System.out.println("TestBeanPostProcessor的postProcessAfterInitialization()方法执行，在Bean="+beanName+"初始化[之后]，可以在这生成代理");
            System.out.println();
        }
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if("lifeCycleSmartInstantiationAwareBeanPostProcessor".equals(beanName)){
            System.out.println("TestBeanPostProcessor的postProcessBeforeDestruction()方法执行，在Bean="+beanName+"自定义销毁方法[之前]");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ATestBeanPostProcessor 通过Aware获得ApplicationContext");
    }
}
