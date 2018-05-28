package com.zyouke.nginx;

import com.zyouke.utils.HttpClientUtil;

import java.util.concurrent.CountDownLatch;

/**
 * NginxWeightTest.java
 * nginx 负载模式权重测试
 * @author zyouke
 * @create 2018/1/9 15:45
 */
public class NginxWeightTest {
    private static int threadNum = 1000;
    private static CountDownLatch cdl = new CountDownLatch(threadNum);

    public static void main(String[] args) {
        // 循环测
        //forTest();
        runnableTest();
    }

    // 多线程测试
    private static void runnableTest(){
        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String jieguo = HttpClientUtil.doGet("http://192.168.0.107/zyouke_web/nginx/nginxWeight.do");
                    System.out.println(jieguo);
                }
            }).start();
            cdl.countDown();
        }
    }
    // 循环测试
    private static void forTest(){
        for (int i = 0; i < threadNum; i++) {
            String jieguo = HttpClientUtil.doGet("http://192.168.0.107/zyouke_web/nginx/nginxWeight.do");
            System.out.println(jieguo);
        }
    }
}
