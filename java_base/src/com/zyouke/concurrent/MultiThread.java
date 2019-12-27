package com.zyouke.concurrent;

import org.junit.Test;

/**
 * @Author: zhoujun
 * 多线程相关测试
 */
public class MultiThread {
    private final Object object = new Object();

    @Test
    public void notifyAndWaitTest(){
        new Thread(() -> {
            synchronized (object){
                try {
                    System.out.println("wait前。。。。。。。");
                    object.wait();
                    System.out.println("wait后。。。。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (object){
                try {
                    System.out.println("notify前。。。。。。。");
                    Thread.sleep(1000);
                    object.notify();
                    System.out.println("notify后。。。。。。。");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
