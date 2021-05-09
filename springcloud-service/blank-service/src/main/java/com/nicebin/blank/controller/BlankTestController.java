package com.nicebin.blank.controller;

import com.nicebin.common.entity.ResultJson;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/8 10:55
 */
@RestController
@RequestMapping("/test")
public class BlankTestController {

    @PostMapping(value = "/getMessage")
    public String comfireMessage(HttpServletRequest request, @RequestBody String message){
        String token = request.getHeader("token");
        System.out.println("Blank收到token "+token);
        String newMessage  = "Blank收到消息 "+message;
        System.out.println(newMessage);

        System.out.println("休息完毕");
        return newMessage;
    }

    @RequestMapping("/throwExceptionTest")
    public String throwExceptionTest(@RequestBody String msg){
        System.out.println(msg);
        throw new RuntimeException("抛出异常测试");
    }

    @PostMapping(value = "/getSlowMessage")
    public String getSlowMessage(HttpServletRequest request, @RequestBody String message){
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName+" 开始休息");
        try{
            Thread.sleep(5 * 1000);
        }catch (Exception e){

        }
        System.out.println(threadName+" 休息完毕");
        return message;
    }
}
