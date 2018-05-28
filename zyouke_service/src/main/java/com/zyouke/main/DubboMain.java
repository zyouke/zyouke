package com.zyouke.main;

import com.alibaba.dubbo.common.extension.Adaptive;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * DubboMain.java
 * dubbo 测试入口
 * @author zyouke
 * @create 2017/11/16 10:22
 */
public class DubboMain {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml","dubbo_provider_1.xml");
        context1.start();
        System.out.println("Dubbo provider start...");
        ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext("applicationContext.xml","dubbo_provider_2.xml");
        context2.start();
        System.out.println("Dubbo provider start...");
        try {
            System.in.read();   // 按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
