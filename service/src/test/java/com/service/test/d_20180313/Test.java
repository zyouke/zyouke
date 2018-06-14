package com.service.test.d_20180313;

import com.zyouke.jdk.thread.ExceptionThread;
import com.zyouke.jdk.thread.LockDemo;
import com.zyouke.jdk.thread.SynchronizedDemo;

public class Test {

    @org.junit.Test
    public void test1(){
        final SynchronizedDemo sd = new SynchronizedDemo();
        final String str = new String();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sd.setStr(str);
                sd.testSynchronized();
            }
        });
        thread1.setName("aaaaaa");
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                sd.setStr(str);
                sd.testSynchronized();
            }
        });
        thread2.setName("bbbbbbb");
        thread2.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                new LockDemo().testLock();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new LockDemo().testLock();
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test3(){
        new Thread(new ExceptionThread()).start();
        try {
            Thread.sleep(2000);
            System.out.println("这是主线程---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}