package com.service.test;

import com.zyouke.bean.Area;
import com.zyouke.service.IAreaService;
import com.zyouke.utils.HttpClientUtil;
import com.zyouke.utils.ThreadUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebTest {

    @Test
    public void test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mvc.xml");
        final IAreaService areaService = (IAreaService) context.getBean("areaService"); // 获取远程服务代理
        for (int i = 0; i < 10; i++) {
            List<Area> list = areaService.findList(0, 100);
            System.out.println(list.toString());
        }
    }

    @Test
    public void test3(){
        int threadNum = 800;
        final CountDownLatch downLatch = new CountDownLatch(threadNum);
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        downLatch.await();
                        HttpClientUtil.doPost("http://103.82.53.221:8080/");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            downLatch.countDown();
        }
        executorService.shutdown();
        while (!executorService.isTerminated());
        System.out.println("执行完成了");
    }

    @Test
    public void test4(){
        System.out.println("11111111111111111111111");

    }

}
