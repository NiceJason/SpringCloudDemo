package com.nicebin.business.mapper;

import com.nicebin.business.entity.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-10-11
 */
public interface BusinessMapper extends BaseMapper<Business> {
    void testInsert(Business business);
}
