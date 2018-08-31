package com.leon.security.service.impl;

import com.leon.security.service.HelloService;
import com.leon.security.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: chongwang
 * @Date: 2018/4/30 10:27
 */

@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public String gretting(String name) {
        System.out.println("grettind");
        return "hello" + name;
    }
}
