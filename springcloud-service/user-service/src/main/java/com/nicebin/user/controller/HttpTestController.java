package com.nicebin.user.controller;

import com.nicebin.common.entity.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author DiaoJianBin
 * @Description 专门测试http相关
 * @Date 2021/4/20 8:24
 */
@Controller
@RequestMapping("/httpTest")
public class HttpTestController {

    @RequestMapping("/refreshResult")
    @ResponseBody
    public ResultJson refreshResult(){
        return new ResultJson("成功触发刷新");
    }

    @RequestMapping("/refreshTest1")
    public void refreshTest1(HttpServletRequest request,HttpServletResponse response) throws Exception{
        System.out.println("进入refreshTest1");
        String testMeta = "<meta http-equiv='refresh' content='3;url=http://localhost:80/httpTest/refreshResult'/>恭喜您登录成功，将在3秒后跳转到首页，如果没有跳转，请点击<a href=''>超链接</a>";

        request.setAttribute("test-meta",testMeta);
        request.getRequestDispatcher("/jumpHtml").forward(request,response);

        //response.sendRedirect("http://localhost:80/html/refreshTest1.html");

        System.out.println("refreshTest1结束");
    }

    @RequestMapping("/jumpHtml")
    public ModelAndView jumpHtml(HttpServletRequest request, HttpServletResponse response){
        System.out.println("进入jumpHtml");
        return new ModelAndView("refreshTest-1");
    }
}
