package com.nicebin.api.provider.feign;

import com.nicebin.api.core.Result;
import com.nicebin.api.provider.feign.factory.RpcApiFallballFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author DiaoJianBin
 * @Description 访问BusinessService服务的接口
 *              有2种回退策略
 *              1.fallback = BusinessServiceTestClientFallback.class
 *              2.fallbackFactory = BusinessServiceTestFallballFactory.class
 *              当两者存在时，只能有一种生效，fallback优先级高于fallbackFactory
 *              但是fallback无法处理异常，就是你不知道啥原因进入了降级
 *              推荐用fallback优先级高于fallbackFactory
 * @Date 2021/3/11 11:23
 */
@FeignClient(value = "provider-service",decode404 = true,fallbackFactory = RpcApiFallballFactory.class)
public interface RpcApi {

    @GetMapping(value = "/rpc/getMessage/{message}")
    String comfireMessage(@PathVariable(value = "message")String message);

    @RequestMapping(value = "/rpc/testFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    Result testFile(@RequestPart(value = "files") MultipartFile[] files, @RequestParam(value = "msg") String msg);

    /**
     * 即使目标实际Controller的方法有HttpServletRequst
     * 但是不需要传此参数，就不用写
     * @param message
     * @return
     */
    @PostMapping(value = "/rpc/getSlowMessage")
    String getSlowMessage(@RequestBody String message);

    /**
     * 测试seata分布式事务
     * @return
     */
    @PostMapping("/seata/insertBusiness")
    String insertBusiness();
}
