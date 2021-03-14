package com.nicebin.user.feign.feign_fallback;

import com.nicebin.common.constant.ErrorCodeConsts;
import com.nicebin.common.entity.ResultJson;
import com.nicebin.user.feign.feign_client.BusinessServiceTestClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author DiaoJianBin
 * @Description BusinessServiceTestClient服务降级的地方
 * @Date 2021/3/12 16:00
 */
@Component
public class BusinessServiceTestClientFallback implements BusinessServiceTestClient {
    @Override
    public String comfireMessage(String message) {
        System.out.println("comfireMessage 方法服务降级");
        return "comfireMessage 方法服务降级";
    }

    @Override
    public ResultJson testFile(MultipartFile[] files, String msg) {
        System.out.println("testFile 方法服务降级");
        return new ResultJson(ErrorCodeConsts.DEGRADATION,"testFile 方法服务降级");
    }
}
