package com.nicebin.user.controller;

import com.alibaba.csp.sentinel.context.ContextUtil;
import com.nicebin.user.entity.TestResource;
import com.nicebin.user.service.SentinelTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/18 11:07
 */
@Controller
@RestController
@RequestMapping("/sentinelTest")
public class SentinelTestController {
    @Autowired
    SentinelTestService sentinelTestService;

    @RequestMapping("/getResource1")
    public String getResource1(@RequestParam("name") String name,@RequestParam("count") int count){
        TestResource testResource = new TestResource("宝贵的资源",10);
        return sentinelTestService.getResource1(name,count,testResource);
    }

    @RequestMapping("/getResource2")
    public String getResource2(@RequestParam("name") String name,@RequestParam("count") int count){
        TestResource testResource = new TestResource("宝贵的资源",10);
        return sentinelTestService.getResource2(name,count,testResource);
    }

    @RequestMapping("/getResource3")
    public String getResource3(@RequestParam("name") String name,@RequestParam("count") int count){
        TestResource testResource = new TestResource("宝贵的资源",33);
        return sentinelTestService.getResource3(name,count,testResource);
    }
}
