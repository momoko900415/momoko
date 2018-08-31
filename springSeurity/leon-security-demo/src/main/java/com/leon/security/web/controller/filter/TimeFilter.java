package com.leon.security.web.controller.filter;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Auther: chongwang
 * @Date: 2018/5/6 15:02
 */
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("time filter start");
        long l = System.currentTimeMillis();
        long time = new Date().getTime();
        long time1 = Timestamp.valueOf(LocalDateTime.now()).getTime();
        System.out.println(l + "===" + time + "===" + time1);
        chain.doFilter(request,response);
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destory");
    }
}
