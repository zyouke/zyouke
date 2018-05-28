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

    private ThreadUtil(){}
    /**
     * @Instructions: 模拟并发
     * @param threadNum 模拟并发数
     * @param runnable 当前执行的线程
     * @Author: zyouke
     * @Date: 2017/11/16 0016 22:41
     */
    public static void concurrentRequest(int threadNum,CountDownLatch countDownLatch,Runnable runnable){
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            countDownLatch.countDown();
        }
        try {
            Thread.sleep(1000*60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("并发模拟请求执行完毕..............");
    }
}
