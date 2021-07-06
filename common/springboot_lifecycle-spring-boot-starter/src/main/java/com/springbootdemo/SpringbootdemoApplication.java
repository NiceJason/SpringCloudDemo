package com.springbootdemo;

import com.springbootdemo.test.bean_life.bean.third.IStudent;
import com.springbootdemo.test.bean_life.bean.third.ITeacher;
import com.springbootdemo.test.bean_life.bean.third.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/6/23 11:23
 */
@SpringBootApplication
public class SpringbootdemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =SpringApplication.run(SpringbootdemoApplication.class);
        ITeacher teacher = context.getBean(ITeacher.class);
        teacher.educate();

        IStudent student = context.getBean(IStudent.class);
        student.study();
    }
}
