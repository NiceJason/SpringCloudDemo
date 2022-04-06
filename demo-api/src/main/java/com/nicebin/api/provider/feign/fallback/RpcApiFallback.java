package com.nicebin.api.provider.feign.fallback;

import com.nicebin.api.core.ResultCode;
import com.nicebin.api.core.Result;
import com.nicebin.api.provider.feign.RpcApi;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author DiaoJianBin
 * @Description BusinessServiceTestClient服务降级的地方
 * @Date 2021/3/12 16:00
 */
public class RpcApiFallback implements RpcApi {
    @Setter
    Throwable cause;

    @Override
    public String comfireMessage(String message) {
        System.out.println("FeignClient：comfireMessage 方法服务降级");
        return "FeignClient：comfireMessage 方法服务降级";
    }

    @Override
    public Result testFile(MultipartFile[] files, String msg) {
        System.out.println("FeignClient：testFile 方法服务降级");
        return new Result(ResultCode.DEGRADATION,"FeignClient：testFile 方法服务降级");
    }


    @Override
    public String getSlowMessage(String message) {
        System.out.println("FeignClient：getSlowMessage 方法服务降级");
        return "FeignClient：getSlowMessage 方法服务降级";
    }

    @Override
    public String insertBusiness() {
        System.out.println("FeignClient：insertBusiness 方法服务降级");
        return "FeignClient：insertBusiness 方法服务降级";
    }
}
