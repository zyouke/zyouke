package com.zyouke.utils;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

/**
 * ThreadUtil.java
 * 线程工具类
 * @author zyouke
 * @create 2017/11/16 22:19
 */
public class ThreadUtil {

    private ThreadUtil(){
    }

    /**
     * 线程休眠
     * @param millis
     */
    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前线程的名称
     * @return
     */
    public static String getThreadName(){
        return Thread.currentThread().getName();
    }
}
