package com.nicebin.api.dubbo.provider.api;

import com.nicebin.api.dubbo.provider.dto.BusinessDTO;
import com.nicebin.api.dubbo.provider.vo.BusinessVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DiaoJianBin
 * @since 2021-10-11
 */
public interface BusinessService{
    BusinessVO insertBusiness(BusinessDTO businessDTO);

    void multipleSearch(@NotNull(message = "code不能为空")String code);
}
