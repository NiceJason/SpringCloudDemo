package com.nicebin.business.controller;

import com.nicebin.common.entity.ResultJson;
import com.nicebin.common.exception.SystemException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author DiaoJianBin
 * @Description 测试控制器，主要用于测试功能
 * @Date 2021/3/8 10:55
 */
@RestController
@RequestMapping("/test")
public class BusinessTestController {

    @GetMapping(value = "/getMessage/{message}")
    public String comfireMessage(@PathVariable(value = "message")String message){
        String newMessage  = "Bussiness收到消息 "+message;
        System.out.println(newMessage);
        return newMessage;
    }

    /**
     * 测试Feign上传文件
     * 利用UserTestController的testFile方法
     * @param files
     * @param msg
     * @return
     */
    @RequestMapping(value = "/testFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultJson testFile(@RequestPart(value = "files") MultipartFile[] files,@RequestParam(value = "msg") String msg){
        System.out.println("进入了testFile方法 msg="+msg);
        try {
            Thread.sleep(10 * 1000);
        }catch (Exception e){

        }
        System.out.println("testFile 接收到文件：");
        for (MultipartFile file :
                files) {
            System.out.println(file.getOriginalFilename());
        }
        //把msg传回去
        return new ResultJson(msg);
    }
}
