package com.zyouke.concurrent;

/**
 * @Author: zhoujun
 */
public class DeadLock {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a){
                System.out.println(Thread.currentThread().getName() + "获取锁a对象");
                synchronized (b){
                    System.out.println(Thread.currentThread().getName() + "获取锁b对象");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (b){
                System.out.println(Thread.currentThread().getName() + "获取锁b对象");
                synchronized (a){
                    System.out.println(Thread.currentThread().getName() + "获取锁a对象");
                }
            }
        }).start();
    }
}
