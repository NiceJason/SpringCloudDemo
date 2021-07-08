package com.springbootdemo.test.bean_life.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author DiaoJianBin
 * @Description 测试Bean的生命周期
 *
 * 一般情况下，初始化的每个阶段只要一个方法去实现即可
 * 这里将Spring的接口和jar定义的注解一起使用，主要是了解下同一阶段两者的执行顺序
 *
 * @Date 2021/7/2 14:21
 */
@Component
public class LifeCycleBean implements InitializingBean, DisposableBean, ApplicationContextAware, SmartLifecycle {

    //SmartLifeCycle使用的变量
    private volatile boolean isRunning = false;

    /**
     * 自己引用自己，也能触发引用方法
     */
    @Autowired
    LifeCycleBean lifeCycleBean;

    public LifeCycleBean(){
        System.out.println("LifeCycleBean的无参构造方法被执行");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("LifeCycleBean ApplicationContextAware进行赋值");
    }

    @PostConstruct
    public void jarPostConstruct(){
        System.out.println("LifeCycleBean @PostConstruct修饰的方法执行");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("LifeCycleBean afterPropertiesSet()方法执行");
    }

    @PreDestroy
    public void jarDestory(){
        System.out.println("LifeCycleBean @PreDestroy修饰的方法执行");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("LifeCycleBean destroy()方法执行");
    }

    @Override
    public void start() {
        isRunning = true;
        System.out.println("LifeCycleBean start()方法执行，组件容器[启动]");
    }

    @Override
    public void stop() {
        isRunning = true;
        System.out.println("LifeCycleBean stop()方法执行，组件容器[关闭]");
    }

    @Override
    public boolean isRunning() {
        System.out.println("LifeCycleBean isRunning()方法被执行，当前运行状态="+isRunning);
        return isRunning;
    }
}
