package com.zyouke.spring.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * MyApplicationListener.java
 * 基于spring的自定义Listener
 * @author zyouke
 * @create 2017/12/11 9:56
 */
@Component
public class MyApplicationListener implements ApplicationListener {
    private static final Logger logger = Logger.getLogger(MyApplicationListener.class);
    /**
     * @Instructions: 实现 onApplicationEvent方法,虽然像是一个普通的方法但在spring初始化的时候调用
     * @Author: zyouke
     * @Date: 2017/12/11 9:57
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.info("spring初始化完成,等待执行业务......");
    }
}
