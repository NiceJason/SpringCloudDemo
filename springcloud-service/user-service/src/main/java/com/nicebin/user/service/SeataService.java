package com.nicebin.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nicebin.user.entity.User;
import com.nicebin.user.feign.feign_client.BusinessServiceTestClient;
import com.nicebin.user.mapper.UserMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试Seata分布式事务
 *
 * @Author DiaoJianBin
 * @Date 2021/10/11 15:42
 */
@Service
public class SeataService extends ServiceImpl<UserMapper, User> {

    @Autowired
    BusinessServiceTestClient businessServiceTestClient;

    @GlobalTransactional
    public void insertUserAndBusiness(){
        User user = new User();
        user.setId(100L);
        user.setName("张三");
        baseMapper.testInsert(user);

        businessServiceTestClient.insertBusiness();
//        throw new SystemException("自定义异常");
    }
}
