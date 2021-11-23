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
public class SeataController {

    @Autowired
    SeataService seataService;

    /**
     * 测试分布式事务
     * @return
     */
    @RequestMapping("/insertUserAndBusiness")
    public String insertUserAndBusiness(){
        seataService.insertUserAndBusiness();
        return "成功";
    }

    /**
     * 测试分布式事务异常回滚
     * @return
     */
    @RequestMapping("/insertThrowEx")
    public String insertThrowEx(){
        seataService.insertThrowEx();
        return "成功";
    }
}
