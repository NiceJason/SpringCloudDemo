package com.nicebin.user.controller;

import com.nicebin.user.service.SeataService;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DiaoJianBin
 * @Date 2021/10/11 15:37
 */
@RestController
@RequestMapping("/seata")
public class SeataTestController {

    @Autowired
    SeataService seataService;

    /**
     * 测试分布式事务
     * @return
     */
    @RequestMapping("/insertUserAndBusiness")
    public String insertUserAndBusiness(){
        seataService.insertUserAndBusiness();
        GlobalTransaction context = GlobalTransactionContext.getCurrent();
        return "成功";
    }
}
