package com.nicebin.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nicebin.user.entity.User;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-04-14
 */
public interface UserMapper extends BaseMapper<User> {
    void testInsert(User user);

    User testSelect();
}
