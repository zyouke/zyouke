package com.service.test;

import com.zyouke.utils.HttpClientUtil;
import org.apache.http.client.methods.HttpPost;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

/**
 * Main.java
 * 使用main方法测试
 * @author zyouke
 * @create 2018/1/17 10:55
 */
public class Main {

    public static void main(String[] args) {
        long sysTime = System.currentTimeMillis();
        do {
            Main main = new Main();
            main.testTomcatNio();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (System.currentTimeMillis() - sysTime < 15000);
    }

    private void testTomcatNio(){
        int threadNum = 200;
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        final Map<String,String> param = new HashMap<String,String>(1);
        param.put("userCode","1002");
        final Queue<HttpPost> pool = HttpClientUtil.createHttpClientPool("http://127.0.0.1:8080/uds/user/getUserAuthStatus.do", param, threadNum * 2);
        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpPost httpPost = pool.poll();
                    System.out.println("生产线程:" + Thread.currentThread().getName());
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String postStr = HttpClientUtil.doPostByPool(httpPost);
                    System.out.println("------------" + postStr);
                }
            }).start();
            cdl.countDown();
        }
    }


}
