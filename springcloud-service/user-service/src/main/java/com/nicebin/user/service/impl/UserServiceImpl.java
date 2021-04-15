package com.nicebin.user.service.impl;

import com.nicebin.user.entity.User;
import com.nicebin.user.mapper.UserMapper;
import com.nicebin.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-04-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
