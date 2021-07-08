package com.springbootdemo.test.bean_life.bean.entity;

/**
 * @Author DiaoJianBin
 * @Description 第三方中间件的教师，用来替换系统的教师
 * @Date 2021/7/6 15:17
 */
public class CustomTeacher implements ITeacher{

    @Override
    public String getName() {
        return "第三方老师";
    }

    @Override
    public void educate() {
        System.out.println(getName()+"开展教育工作");
    }
}
