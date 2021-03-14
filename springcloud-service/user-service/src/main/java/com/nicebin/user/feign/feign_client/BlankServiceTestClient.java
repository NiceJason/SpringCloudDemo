package com.nicebin.user.feign.feign_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/12 11:54
 */
@FeignClient(value = "blank-service",path = "/test",decode404 = true)
public interface BlankServiceTestClient {
    @PostMapping(value = "/getMessage")
    String comfireMessage(@RequestBody String message);
}
