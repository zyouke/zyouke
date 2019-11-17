package com.zyouke.thread;

/**
 * @Author: zhoujun
 */
public class Notify {
    private final static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    for (int i = 0; i < 100; i++) {
                        try {
                            lock.notify();
                            Thread.sleep(10);
                            System.out.println(Thread.currentThread().getName() + "------------" + i);
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    for (int i = 0; i < 100; i++) {
                        try {
                            lock.notify();
                            Thread.sleep(10);
                            System.out.println(Thread.currentThread().getName() + "------------" + i);
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

}
