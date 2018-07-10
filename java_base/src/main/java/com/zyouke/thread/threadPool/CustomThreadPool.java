package com.zyouke.thread.threadPool;

import java.util.concurrent.*;

/**
 * 线程池
 * @Title: CustomThreadPool.java
 * @author 周俊
 * @date 2018年1月15日 下午1:34:11
 */
public class CustomThreadPool {
    
    private int corePoolSize; //线程池维护线程的最少数量 
    private int maxPoolSize; //线程池维护线程的最大数量
    // 同時運行的線程數量
    private BlockingQueue<Runnable> workThreads=new LinkedBlockingDeque<Runnable>(maxPoolSize);//当前工作线程
    // 任务线程队列
    private BlockingQueue<Runnable> tasks=new LinkedBlockingDeque<>(10);
    public CustomThreadPool() {
        new CustomThreadPool(Runtime.getRuntime().availableProcessors(),Runtime.getRuntime().availableProcessors()*2,300L);
    }

    public CustomThreadPool(int corePoolSize, int maxiPoolSize, long keepAliveTime) {
        super();
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxiPoolSize;
    }

    public void execute(Runnable runnable){
        if (workThreads.size()){

        }
    }
    

}
