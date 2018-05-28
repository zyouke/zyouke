package com.zyouke.jdk.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SynchronizedDemo.java
 *
 * @author zyouke
 * @create 2018/3/13 11:30
 */
public class LockDemo {
    private static Lock lock = new ReentrantLock();
    public void testLock(){
        try {
            lock.lock();
            System.out.println("加锁的时间:" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
            System.out.println("释放锁的时间:" + System.currentTimeMillis());
        }

    }





}
