package com.nicebin.user.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.alibaba.fastjson.JSONObject;
import com.nicebin.common.entity.ResultJson;
import com.nicebin.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author DiaoJianBin
 * @Description 专门测试MybatisPlus和Druid的入口
 *              主要测试数据库在高并发的状态
 *              同时观察的调整Druid对于高并发的影响
 * @Date 2021/4/15 21:24
 */
@RestController
@RequestMapping("/mybatisTest")
public class MybatisTestController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 测试一下乐观锁
     * @param param
     * @return
     */
    @RequestMapping("/testVersion")
    public ResultJson testVersion(@RequestBody JSONObject param){
        String email = param.getString("email");
        Integer age = param.getInteger("age");

        userService.testVersion(email,age);

        return new ResultJson("更新成功 传入的参数email="+email+"  age="+age);
    }

    /**
     * 测试一下Mapper里定义的方法
     * @return
     */
    @RequestMapping("/testMapperMethod")
    public ResultJson testMapperMethod(){
        return new ResultJson(userService.testMapperMethod());
    }

    /**
     * 获取Druid的监控信息
     * @return
     */
    @RequestMapping("/getDruidStat")
    public ResultJson getDruidStat(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("测试Session监控","看生不生效");
        DruidStatManagerFacade managerFacade = DruidStatManagerFacade.getInstance();
        return new ResultJson(managerFacade.getDataSourceStatDataList());
    }
}
