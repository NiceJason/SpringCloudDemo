package com.nicebin.user.controller;

import com.nicebin.common.entity.ResultJson;
import com.nicebin.user.entity.AnnotationTestSpringCloud;
import com.nicebin.user.feign.feign_client.BlankServiceTestClient;
import com.nicebin.user.feign.feign_client.BusinessServiceTestClient;
import com.springclouddemo.redis.cache.CacheThreadPool;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/3 17:15
 */
@RestController
@RefreshScope
@RequestMapping("/test")
@Slf4j
public class UserTestController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedissonClient redissonClient;

    //系统缓存过期时间允许最小值（秒）
    @Value("${system.cache.minExpire}")
    private long minExpire;

    //系统缓存过期时间允许最大值（秒）
    @Value("${system.cache.maxExpire}")
    private long maxExpire;

    @Autowired
    private AnnotationTestSpringCloud annotationTestSpringCloud;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    BusinessServiceTestClient businessServiceTestClient;

    @Autowired
    BlankServiceTestClient blankServiceTestClient;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return minExpire+" "+maxExpire;
    }

    @GetMapping("/config/getAnnotationTestSpringCloud")
    public String getAnnotationTestSpringCloud() {
        return annotationTestSpringCloud.toString();
    }

    /**
     *
     * @return
     */
    @GetMapping("/sendMessageToBlank")
    public String sendMessageToBlank(){
       return  restTemplate.postForObject("http://blank-service/test/getMessage","userMessage",String.class);
    }

    @GetMapping("/sendMessageToBusiness/{msg}")
    public String sendMessageToBusiness(@PathVariable(value = "msg")String msg){
        log.info("日志记录 msg  = {}",msg);
        return  restTemplate.getForObject("http://business-service/test/getMessage/businessMessage",String.class);
    }

    /**
     * 测试Ribbon重试
     * @return
     */
    @GetMapping("/getSlowMessage")
    public String getSlowMessage(){
        return  restTemplate.postForObject("http://blank-service/test/getSlowMessage","userMessage",String.class);
    }

    @SneakyThrows
    @GetMapping("/testRedis")
    public String testRedis(HttpServletRequest request){
        //测试redis
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("user-service","redis-test");

        //测试SpringSession
        HttpSession httpSession = request.getSession();

        //测试Redission
        RLock lock = redissonClient.getLock("user-service-lock");
        lock.lock();
        Thread.sleep(2 * 1000);
        lock.unlock();

        //测试CacheThreadPool是否初始化成功
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        CacheThreadPool cacheThreadPool = applicationContext.getBean(CacheThreadPool.class);
        System.out.println("缓存线程池大小为 = "+cacheThreadPool.getThreadPoolExecutor().getMaximumPoolSize());
        return "ok";
    }

    /**
     * 测试Feign上传文件
     * 利用postman访问此方法
     * @param files
     * @param msg
     * @return
     */
    @RequestMapping(value = "/testFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultJson testFile(@RequestPart(value = "files") MultipartFile[] files, @RequestParam(value = "msg") String msg){
        System.out.println("UserTestController 接收到文件：");
        for (MultipartFile file :
                files) {
            System.out.println(file.getOriginalFilename());
        }
        ResultJson resultJson = businessServiceTestClient.testFile(files,msg);
        //把msg传回去
        return new ResultJson("收到msg = "+resultJson.getMsg());
    }

    @GetMapping("/sendMessageToBlankFeign")
    public String sendMessageToBlankFeign(){
        String longMsg = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"+
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"+
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"+
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"+
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"+
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"+
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        return  blankServiceTestClient.comfireMessage(longMsg);
    }

    /**
     * 测试路由
     * @return
     */
    @RequestMapping("/testGateway")
    public String testGateway(HttpServletRequest request) throws Exception{

        String appId = request.getParameter("appId");
        String msg = request.getParameter("msg");

        String str = "appId="+appId +" mag="+msg;
        System.out.println(str);

        //这里是获取不到东西的，两个Session不互通，要自己弄分布式Session
        HttpSession session = request.getSession();
        System.out.println("获取到从Gateway设置在Session里的值："+session.getAttribute("Gateway"));

        //获取Gateway设置的头部
        String headerMsg = request.getHeader("GatewayKey-header");
        System.out.println("获取Gateway设置的头部 = "+ URLDecoder.decode(headerMsg,"UTF-8"));

        //获取Gateway设置的cookie，Gateway操作cookie只能读，获取不到东西的
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie :
                cookies) {
            System.out.println("收到的cookie name= "+cookie.getName()+" value="+cookie.getValue());
        }
        //获取Gateway设置的param，Gateway操作param只能读，获取不到东西的
        String param = request.getParameter("GatewayKey-param");
        System.out.println("获取Gateway设置的param = "+param);

        return str;
    }
}
