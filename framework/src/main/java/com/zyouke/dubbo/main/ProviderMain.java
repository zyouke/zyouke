package com.zyouke.dubbo.main;

import com.zyouke.dubbo.config.DubboServiceProviderConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProviderMain {
    public static void main(String[] args){
        try{
            AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(DubboServiceProviderConfig.class);
            context.start();
            System.out.println("生存者启动成功。。。。");
            System.in.read();
        }catch(Exception e){
            System.out.println("生存者启动失败。。。。");
            e.printStackTrace();
        }
    }
}
