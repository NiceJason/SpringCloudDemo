package com.nicebin.user.feign.feign_client;

import com.nicebin.common.entity.ResultJson;
import com.nicebin.user.feign.feign_fallback_factory.BusinessServiceTestFallballFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
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
@Primary
@FeignClient(value = "business-service",path = "/test",decode404 = true,fallbackFactory = BusinessServiceTestFallballFactory.class)
public interface BusinessServiceTestClient {

    @GetMapping(value = "/getMessage/{message}")
    String comfireMessage(@PathVariable(value = "message")String message);

    @RequestMapping(value = "/testFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    ResultJson testFile(@RequestPart(value = "files") MultipartFile[] files,@RequestParam(value = "msg") String msg);

    @RequestMapping(value = "/throwExceptionTest",method = RequestMethod.POST)
    String throwExceptionTest(@RequestBody String msg);

    /**
     * 即使目标实际Controller的方法有HttpServletRequst
     * 但是不需要传此参数，就不用写
     * @param message
     * @return
     */
    @PostMapping(value = "/getSlowMessage")
    String getSlowMessage(@RequestBody String message);
}
