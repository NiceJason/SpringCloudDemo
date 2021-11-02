package com.nicebin.business.service.impl;

import com.nicebin.business.entity.Business;
import com.nicebin.business.mapper.BusinessMapper;
import com.nicebin.business.service.BusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nicebin.common.exception.SystemException;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-10-11
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

    @Autowired
    HttpServletRequest request;

    @Transactional
    public void insertBusiness(){
        System.out.println("头部获取的xid="+request.getHeader(RootContext.KEY_XID));
        System.out.println("RootContent获取的xid="+RootContext.getXID());

        Business business = new Business();
        business.setId(200);
        business.setName("国际公司");
        baseMapper.testInsert(business);
//        throw new SystemException("假设异常用于分布式事务回滚");
    }
}
