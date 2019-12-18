package com.zyouke.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhoujun
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        //nonfairSync();
        fairSync();
    }

    /**
     * 非公平锁
     */
    private static void nonfairSync(){
        ReentrantLock reentrantLock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println("-----" + Thread.currentThread().getName() + "加入获取锁");
                reentrantLock.lock();
                System.out.println("-----" + Thread.currentThread().getName() + "获取锁");
                reentrantLock.unlock();
            });
        }
        executorService.shutdown();
    }
    /**
     * 公平锁
     */
    private static void fairSync(){
        ReentrantLock reentrantLock = new ReentrantLock(true);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println("-----" + Thread.currentThread().getName() + "加入获取锁");
                reentrantLock.lock();
                System.out.println("-----" + Thread.currentThread().getName() + "获取锁");
                reentrantLock.unlock();
            });
        }
        executorService.shutdown();
    }

}
