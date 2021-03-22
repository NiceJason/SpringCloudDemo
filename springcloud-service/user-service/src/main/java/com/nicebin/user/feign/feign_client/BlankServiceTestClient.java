package com.nicebin.user.feign.feign_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/12 11:54
 */
@FeignClient(value = "blank-service",path = "/test",decode404 = true)
public interface BlankServiceTestClient {
    @PostMapping(value = "/getMessage")
    String comfireMessage(@RequestBody String message);

    @RequestMapping("/throwExceptionTest")
    String throwExceptionTest(@RequestBody String msg);

    @PostMapping(value = "/getSlowMessage")
    String getSlowMessage(@RequestBody String message);
}
