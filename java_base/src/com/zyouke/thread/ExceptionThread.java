package com.zyouke.thread;

/**
 * ExceptionThread.java
 *
 * @author zyouke
 * @create 2018/3/22 9:50
 */
public class ExceptionThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException("这是测试子线程异常...........");
    }
}
