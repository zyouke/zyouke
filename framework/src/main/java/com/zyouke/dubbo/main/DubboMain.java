package com.zyouke.dubbo.main;




import org.apache.commons.lang.SystemUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

import static org.apache.commons.lang.SystemUtils.OS_NAME;

/**
 * DubboMain.java
 * dubbo 测试入口
 * @author zyouke
 * @create 2017/11/16 10:22
 */
public class DubboMain {
    // java -cp framework.jar com.zyouke.dubbo.main.DubboMain
    public static void main(String[] args){
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml","dubbo/dubbo_provider_1.xml");
        context1.start();
        System.out.println("Dubbo provider1 start...");
        ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext("applicationContext.xml","dubbo/dubbo_provider_2.xml");
        context2.start();
        System.out.println("Dubbo provider2 start...");
        String OS_NAME = SystemUtils.OS_NAME;
        System.out.println("-----------------------------当前操作系统为 : " + OS_NAME + "---------------------------------");
        if (OS_NAME.indexOf("Windows") >= 0){
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            while (true){}
        }
    }
}
