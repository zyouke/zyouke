package com.zyouke.jdk.thread;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SynchronizedDemo.java
 *
 * @author zyouke
 * @create 2018/3/13 11:30
 */
public class SynchronizedDemo {

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void testSynchronized(){
        synchronized (str){
            System.out.println(Thread.currentThread().getName() + "获取锁进入方法........" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }





}
