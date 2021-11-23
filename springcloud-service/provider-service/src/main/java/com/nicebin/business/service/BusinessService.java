package com.nicebin.business.service;

import com.nicebin.business.entity.Business;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-10-11
 */
public interface BusinessService extends IService<Business> {
    void insertBusiness();
}
