package com.zyouke.dubbo.main;




import com.alibaba.dubbo.config.ProtocolConfig;
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
    private static final String ONE = "one";
    private static final String TWO = "two";
    public static void main(String[] args){
        if (args.length > 0){
            if (args[0].equals(ONE)){
                startProviderONE();
            }
            if (args[0].equals(TWO)){
                startProviderTWO();
            }
        }else {
            startProviderONE();
            startProviderTWO();
        }
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

    private static void startProviderONE(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml","dubbo/dubbo_provider_1.xml");
        context.start();
        ProtocolConfig protocolConfig = context.getBean(ProtocolConfig.class);
        System.out.println("Dubbo provider start ==================>" + protocolConfig.toString());
    }
    private static void startProviderTWO(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml","dubbo/dubbo_provider_2.xml");
        context.start();
        ProtocolConfig protocolConfig = context.getBean(ProtocolConfig.class);
        System.out.println("Dubbo provider start ==================>" + protocolConfig.toString());
    }







}
