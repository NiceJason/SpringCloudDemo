package com.nicebin.user.mapper;

import com.nicebin.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-04-14
 */
public interface UserMapper extends BaseMapper<User> {
     void testInsert(User user);

    List<User> testSelect();
}
