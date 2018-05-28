package com.zyouke.main;

import com.zyouke.utils.HttpClientUtil;
import com.zyouke.utils.RandomUtil;
import org.apache.http.client.methods.HttpPost;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * ZyoukeWeb 测试的main
 */
public class ZyoukeWebMain {

    private final static int threadNum = 200;
    private static CountDownLatch cdl = new CountDownLatch(threadNum);
    public static void main(String[] args) {
        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Map<String,String> param = new HashMap<String,String>(1);
                    param.put("uuid", UUID.randomUUID().toString());
                    String postStr = HttpClientUtil.doGet("http://192.168.0.107/zyouke_web/nginx/nginxCache.do",param);
                    System.out.println("------------" + postStr);
                }
            }).start();
            cdl.countDown();
        }
    }
}
