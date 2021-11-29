package com.nicebin.service.servlet_filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *  测试下dubbo访问能否触发servlet的filter
 *  结果是可以的，毕竟连接还是要先经过tomcat的
 *
 * @Author DiaoJianBin
 * @Date 2021/11/26 11:11
 *
 */
@WebFilter(filterName = "servletCustomFilter",urlPatterns = "/*")
public class ServletCustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ServletCustomFilter初始化了");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入了ServletCustomFilter");
        chain.doFilter(request,response);
    }

}
