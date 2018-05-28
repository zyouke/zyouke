package com.zyouke.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// aop 测试 这是一个切面类
@Aspect
@Component("aopTest")
public class AopTest {

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    // 定义一个切点,主要是拦截方法
    @Pointcut("execution(* com.zyouke.aop.*.*(..))")
    public void pointcut(){}

    // 前置通知
    @Before("pointcut()")
    public void executeBefore(){
        System.out.println("方法执行前调用");
        threadLocal.set(System.currentTimeMillis());
    }

    // 环绕通知
    @Around("pointcut()")
    public void executeAround(ProceedingJoinPoint pjp){
        // 获取即将执行的方法名

    }
    // 后置通知
    @After("pointcut()")
    public void executeAfter(){
        System.out.println("方法执行后调用");
        System.out.println(Thread.currentThread().getName() + "方法执行了 :" + (System.currentTimeMillis() - threadLocal.get()));
    }

}