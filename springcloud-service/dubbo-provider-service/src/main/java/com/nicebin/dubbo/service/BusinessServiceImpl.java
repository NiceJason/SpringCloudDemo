package com.nicebin.dubbo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nicebin.api.dubbo.provider.api.BusinessService;
import com.nicebin.api.dubbo.provider.dto.BusinessDTO;
import com.nicebin.api.dubbo.provider.vo.BusinessVO;
import com.nicebin.dubbo.entity.Business;
import com.nicebin.dubbo.mapper.BusinessMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author DiaoJianBin
 * @Date 2021/11/24 9:35
 */
@DubboService(protocol = "dubbo", version = "1.0.0",validation = "true")
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    BusinessMapper businessMapper;

    @Override
    public BusinessVO insertBusiness(BusinessDTO businessDTO) {
        String name = businessDTO.getName();
        Business business = new Business();
        business.setName(name);
        businessMapper.save(business);

        BusinessVO vo = businessMapper.select(business.getBusinessId());
        System.out.println(vo.toString());
        return vo;
    }

    /**
     * 几种操作mybaits的方式
     * @return
     */
    public void multipleSearch(String code){
        System.out.println("接收到code="+code);
        //方式一，mapper及xml的方法，insertBusiness展示过了

        //方式二，单独mapper里的方法，类似JPA，结果是不行的
        //Business business2 = businessMapper.selectByName("七七");
        //System.out.println("方式二访问结果 "+business2.toString());

        //方式三，使用注解
        Business business3 = businessMapper.multipleSearch3("王五");
        System.out.println("方式三访问结果 "+business3.toString());

        //方法四，使用苞米豆的工具，有点类似于JPA的Specification
        QueryWrapper<Business> wrapper = new QueryWrapper<>();
        wrapper.eq("name","李四");
        Page<Business> page = new Page<>(1,2);
        Page<Business> businessPage = businessMapper.selectPage(page, wrapper);

        List<Business> records = businessPage.getRecords();
        System.out.println("方式四访问结果 "+records.toString());
    }
}
