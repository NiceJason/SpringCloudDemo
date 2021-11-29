package com.nicebin.dubbo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nicebin.api.dubbo.provider.vo.BusinessVO;
import com.nicebin.dubbo.entity.Business;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-10-11
 */
public interface BusinessMapper extends BaseMapper<Business> {
    int save(Business business) ;

    BusinessVO select(Long id);

    //这种无法直接访问，不能像JPA那样
    //Business selectByName(String name);

    @Select("<script>" +
            "select * from business where name = #{name}"+
            "</script>")
    Business multipleSearch3(@Param("name") String name);
}
