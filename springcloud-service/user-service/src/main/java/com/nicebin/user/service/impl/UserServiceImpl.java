package com.nicebin.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nicebin.user.entity.User;
import com.nicebin.user.mapper.UserMapper;
import com.nicebin.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-04-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public void testVersion(String email, Integer age) {
        boolean success = false;

        while (!success) {
            //查找出老的user
            User user = getById(1);
            Integer oldAge = user.getAge();
            if (oldAge == null) oldAge = 0;

            user.setEmail(email);
            user.setAge(age + oldAge);

            success = updateById(user);
            System.out.println(Thread.currentThread().getName() + " 更新结果 " + success);
        }
    }
}
