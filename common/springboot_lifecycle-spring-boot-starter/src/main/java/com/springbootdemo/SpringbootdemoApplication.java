package com.springbootdemo;

import com.springbootdemo.test.bean_life.bean.entity.IStudent;
import com.springbootdemo.test.bean_life.bean.entity.ITeacher;
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

        System.out.println("\n=======================程序启动完毕，开始业务逻辑的运行=======================\n");

        ITeacher teacher = context.getBean(ITeacher.class);
        teacher.educate();

        IStudent student = context.getBean(IStudent.class);
        student.study();

        System.out.println("\n=======================业务结束，关闭ApplicationContext容器=======================\n");
        context.close();
    }
}
