package com.nicebin.business.controller;

import com.nicebin.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DiaoJianBin
 * @Date 2021/10/11 16:29
 */
@RestController()
@RequestMapping("/seata")
public class SeataController {
    @Autowired
    BusinessService businessService;

    @PostMapping("/insertBusiness")
    public String insertBusiness(){
        businessService.insertBusiness();
        return "插入商户成功";
    }
}
