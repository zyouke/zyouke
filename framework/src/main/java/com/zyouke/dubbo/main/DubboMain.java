package com.zyouke.dubbo.main;




import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
/**
 * DubboMain.java
 * dubbo 测试入口
 * @author zyouke
 * @create 2017/11/16 10:22
 */
public class DubboMain {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml","dubbo/dubbo_provider_1.xml");
        context1.start();
        System.out.println("Dubbo provider1 start...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* ThreadUtil.sleep(3000);
        ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext("applicationContext.xml","dubbo/dubbo_provider_2.xml");
        context2.start();
        System.out.println("Dubbo provider2 start...");*/
    }
}
