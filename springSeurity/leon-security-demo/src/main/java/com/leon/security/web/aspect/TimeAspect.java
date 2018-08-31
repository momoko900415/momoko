package com.leon.security.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Auther: chongwang
 * @Date: 2018/5/10 21:49
 */
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.leon.security.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("time sapect start");
        Object[] args = proceedingJoinPoint.getArgs();
        //遍历目标参数
        for (Object arg : args) {
            System.out.println("args is " + arg);
        }
        long start = Timestamp.valueOf(LocalDateTime.now()).getTime();
        //执行目标方法
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("aspect 耗时:" + (System.currentTimeMillis() - start));
        System.out.println("time aspect end");
        return proceed;
    }

}
