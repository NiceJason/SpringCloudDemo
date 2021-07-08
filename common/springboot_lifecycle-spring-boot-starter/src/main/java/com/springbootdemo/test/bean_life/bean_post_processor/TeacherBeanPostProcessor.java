package com.springbootdemo.test.bean_life.bean_post_processor;

import com.springbootdemo.test.bean_life.bean.entity.CustomTeacher;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 将中间件的教师替换掉系统的
 * @Date 2021/7/6 15:19
 */
@Component
public class TeacherBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("systemTeacher".equals(beanName)){
            System.out.println("\nTeacherBeanPostProcessor 开始替换教师\n");
            return new CustomTeacher();
        }
        return bean;
    }
}
