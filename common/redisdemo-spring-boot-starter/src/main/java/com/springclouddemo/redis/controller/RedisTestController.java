package com.springclouddemo.redis.controller;

import com.springclouddemo.redis.spring_event.event.SyncEvent;
import com.springclouddemo.redis.spring_event.event.TestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author DiaoJianBin
 * @Description
 * @Date 2021/3/3 8:30
 */
@RestController
public class RedisTestController {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    /**
     * 测试浏览器禁用Cookie对Session的影响
     * @return
     */
    @GetMapping("/sendNoCookie")
    public String sendNoCookie(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cookie[] cookie = request.getCookies();

        String oldUrl = request.getRequestURL().toString();

        System.out.println("session id="+session.getId());

        String encodeURLUrl = response.encodeURL(oldUrl);
        String encodeRedirectURL = response.encodeRedirectURL(oldUrl);
        return "encodeURLUrl = "+encodeURLUrl+" ，encodeRedirectURL = "+encodeRedirectURL;
    }

    /**
     * 测试SpringSession是否生效
     * 可以启动一个RedisdemoApplication(2)来访问此方法
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/testSpringSession")
    public String testSpringSession(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("hello","hello");
        Object port = session.getAttribute("port");
        if(port == null){
            port = request.getServerPort();
            session.setAttribute("port",port);
            return "设置port = "+ port;
        }else{
            return "获得port = "+port;
        }
    }

    /**
     * 测试Spring时间机制
     * @return
     */
    @GetMapping("/sendTestEvent")
    public String sendTestEvent(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("sendTestEvent的Thread为 "+Thread.currentThread().getName());

        applicationEventPublisher.publishEvent(new SyncEvent(new String("source"),"事件message"));
        applicationEventPublisher.publishEvent(new TestEvent(new String("source"),"事件message"));

        System.out.println("事件发送完毕，开始休眠5s");
        Thread.sleep(5 * 1000);
        System.out.println("休眠完毕");
        return "ok";
    }
}
