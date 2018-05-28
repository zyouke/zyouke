package com.zyouke.aop;

import org.springframework.stereotype.Component;

// 服务类
@Component("serviceInfo")
public class ServiceInfo {

    public void serviceFunction(){
        System.out.println("这里执行业务......");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }
}