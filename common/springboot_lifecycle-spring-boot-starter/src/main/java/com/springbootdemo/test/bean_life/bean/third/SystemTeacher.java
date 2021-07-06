package com.springbootdemo.test.bean_life.bean.third;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 系统的教师，当作框架的默认实现类，之后用第三方类去代替
 * @Date 2021/7/6 15:16
 */
@Component
public class SystemTeacher implements ITeacher{

    @Autowired
    IStudent student;

    @Override
    public String getName() {
        return "系统老师";
    }

    @Override
    public void educate() {
        System.out.println(getName()+"开展教育工作");
    }
}
