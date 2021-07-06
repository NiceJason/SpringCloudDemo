package com.springbootdemo.test.bean_life.bean.third;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author DiaoJianBin
 * @Description 学生
 * @Date 2021/7/6 15:30
 */
@Component
public class Student implements IStudent{
    @Autowired
    ITeacher teacher;

    @Override
    public String getName() {
        return "张三";
    }

    public void study(){System.out.println("学生正在请教"+teacher.getName());
    }
}
