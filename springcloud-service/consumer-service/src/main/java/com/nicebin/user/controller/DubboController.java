package com.nicebin.user.controller;

import com.nicebin.api.dubbo.provider.api.BusinessService;
import com.nicebin.api.dubbo.provider.dto.BusinessDTO;
import com.nicebin.api.dubbo.provider.vo.BusinessVO;
import com.nicebin.common.exception.SystemException;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author DiaoJianBin
 * @Date 2021/11/24 16:28
 */
@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @DubboReference(protocol = "dubbo", version = "1.0.0", validation = "true")
    BusinessService businessService;

    @RequestMapping("/insertBusiness")
    public BusinessVO insertBusiness(@RequestBody @Validated BusinessDTO businessDTO) {
        return businessService.insertBusiness(businessDTO);
    }

    @RequestMapping("/multipleSearch")
    public void multipleSearch(@RequestBody(required = false) String code) {
        businessService.multipleSearch(code);
    }
}
