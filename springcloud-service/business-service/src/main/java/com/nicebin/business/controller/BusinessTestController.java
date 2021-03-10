package com.nicebin.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/8 10:55
 */
@RestController
public class BusinessTestController {

    @GetMapping(value = "/getMessage/{message}")
    public String comfireMessage(@PathVariable(value = "message")String message){
        String newMessage  = "Bussiness收到消息 "+message;
        System.out.println(newMessage);
        return newMessage;
    }
}
