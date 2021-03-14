package com.nicebin.user.feign_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/12 11:54
 */
@FeignClient(value = "blank-service",path = "/test",decode404 = true)
public interface BlankServiceTestClient {
    @GetMapping(value = "/getMessage/{message}")
    String comfireMessage(@PathVariable(value = "message")String message);
}
