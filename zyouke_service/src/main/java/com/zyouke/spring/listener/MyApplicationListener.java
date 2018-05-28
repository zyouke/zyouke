package com.zyouke.spring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * MyApplicationListener.java
 * 基于spring的自定义Listener
 * @author zyouke
 * @create 2017/12/11 9:56
 */
@Component
public class MyApplicationListener implements ApplicationListener {
    /**
     * @Instructions: 实现 onApplicationEvent方法,虽然像是一个普通的方法但在spring初始化的时候调用
     * @Author: zyouke
     * @Date: 2017/12/11 9:57
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("spring初始化完成,3秒后执行业务......");
        long s = System.currentTimeMillis();
        int count = 3;
        while (System.currentTimeMillis() - s < 3100){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("-----------" + count-- + "--------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    /**
     * @Instructions: 测试在注册bean的时候是否调用 结果是不调用的
     * @Author: zyouke
     * @Date: 2017/12/11 10:21
     */
    public void test(){
        System.out.println("测试在注册bean的时候是否调用...");
    }
}
