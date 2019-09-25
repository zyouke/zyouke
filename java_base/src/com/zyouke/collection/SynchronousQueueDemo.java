package com.zyouke.collection;

import java.util.UUID;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueueDemo.java
 * 阻塞队列SynchronousQueue 的测试
 * @author zyouke
 * @create 2018/1/18 12:00
 */
public class SynchronousQueueDemo {
    private final static  SynchronousQueue<String> queue = new SynchronousQueue<String>();
    public static void main(String[] args) {
        /*new Thread(new ProducerRunnable()).start();
        new Thread(new CustomerRunnable()).start();*/
        System.out.println(Runtime.getRuntime().availableProcessors());
    }




    /**
     * @Instructions: 生产者线程
     * @Author: zyouke
     * @Date: 2018/1/18 15:24
     */
    static class ProducerRunnable implements Runnable{
        public void run() {
            String str = "";
            while (!str.equals("abcde")){
                str = UUID.randomUUID().toString();
                try {
                    System.out.println("生产者生产产品完毕,500毫秒后出厂");
                    TimeUnit.MILLISECONDS.sleep(500);
                    queue.put(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Instructions: 消费者线程
     * @Author: zyouke
     * @Date: 2018/1/18 15:24
     */
    static class CustomerRunnable implements Runnable{
        public void run() {
            long s = System.currentTimeMillis();
            while (true){
                try {
                    System.out.println("消费者消费产品:" + queue.take());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
