//package com.nicebin.user.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.nicebin.common.entity.ResultJson;
//import com.nicebin.user.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author DiaoJianBin
// * @Description 专门测试MybatisPlus的入口
// *              主要测试数据库在高并发的状态
// *              同时观察的调整Druid对于高并发的影响
// * @Date 2021/4/15 21:24
// */
//@RestController
//@RequestMapping("/mybatisTest")
//public class MybatisTestController {
//
//    @Autowired
//    UserServiceImpl userService;
//
//    @RequestMapping("/testVersion")
//    public ResultJson testVersion(@RequestBody JSONObject param){
//        String email = param.getString("email");
//        Integer age = param.getInteger("age");
//
//        userService.testVersion(email,age);
//
//        return new ResultJson("更新成功 传入的参数email="+email+"  age="+age);
//    }
//}
