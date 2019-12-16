package com.zyouke.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhoujun
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("-------开始执行下步逻辑---------------"));
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "到达集合点～～");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "开始旅行啦～～");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "到达集合点～～");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "开始旅行啦～～");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "到达集合点～～");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "开始旅行啦～～");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }



}
