package com.zyouke.threadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 * @Title: CustomThreadPool.java
 * @author 周俊
 * @date 2018年1月15日 下午1:34:11
 */
public class CustomThreadPool {

    // 获取当前线程数
    private volatile AtomicInteger atomicInteger = new AtomicInteger(0);
    // 初始化线程数量
    private int corePoolSize;
    // 线程池最大线程数量
    private int maximumPoolSize;
    // 缓存队列大小
    private int queueSize;
    // 核心工作的线程数组
    private Worker[] workers;
    // 存放待执行的线程
    private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);


    public CustomThreadPool(int corePoolSize, int maximumPoolSize) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        workers = new Worker[corePoolSize];
    }

    public CustomThreadPool() {
        this(5,10);
    }

    // 执行线程
    public void execute(Runnable command){
        int andIncrement = atomicInteger.getAndIncrement();
        if (andIncrement < corePoolSize){
            workers[andIncrement] = new Worker(command,"thread_"+andIncrement);
            workers[andIncrement].start();
        }else{
            boolean offer = workQueue.offer(command);
            if (!offer){
                System.out.println("线程池已满......");
            }
        }
    }

    // 线程池中线程全部执行完,返回true
    public boolean isTerminated(){
        // 缓存任务的数量
        int size = workQueue.size();
        // 获取当前工作线程的状态
        boolean isRunning = false;
        for (int i = 0; i < workers.length; i++) {
            isRunning = workers[i].isRunning;
            if (isRunning){
                break;
            }
        }
        return size == 0 && (!isRunning);
    }


    /**
     * 获取缓冲区线程
     * @return
     */
    private Runnable getTask(){
        Runnable task = null;
        try {
            task = workQueue.poll(1L,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return task;
    }
    // 内部类,处理线程
    class Worker extends Thread {
        // 执行任务的线程
        private Thread thread;
        // 真正的任务
        private Runnable firstTask;
        // 当前工作线程的状态
        private boolean isRunning = true;

        public Worker(Runnable task,String workerName) {
            this.thread = new Thread(task,workerName);
            this.firstTask = task;
        }

        @Override
        public void run() {
            Runnable firstTask = this.firstTask;
            while (firstTask != null || (firstTask = getTask()) != null){
                isRunning = true;
                // 使用对象调run()方法
                try {
                    firstTask.run();
                } finally {
                    // 防止线程重复执行
                    firstTask = null;
                    isRunning = false;
                }
            }
        }
    }



}
