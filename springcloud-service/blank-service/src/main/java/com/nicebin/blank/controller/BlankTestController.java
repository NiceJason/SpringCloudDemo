package com.nicebin.blank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/8 10:55
 */
@RestController
@RequestMapping("/test")
public class BlankTestController {

    @GetMapping(value = "/getMessage/{message}")
    public String comfireMessage(@PathVariable(value = "message")String message){
        String newMessage  = "Blank收到消息 "+message;
        System.out.println(newMessage);
        try{
            Thread.sleep(11 * 1000);
        }catch (Exception e){

        }
        System.out.println("休息完毕");
        return newMessage;
    }
}
