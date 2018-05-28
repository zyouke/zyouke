package com.zyouke.jdk.threadPool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 * @Title: ThreadPoolTest.java
 * @author 周俊
 * @date 2018年1月15日 下午1:34:11
 */
public class ThreadPoolTest {
    
    private int corePoolSize; //线程池维护线程的最少数量 
    private int maximumPoolSize; //线程池维护线程的最大数量 
    private long keepAliveTime; //线程池维护线程所允许的空闲时间 
    
    public ThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
	super();
	this.corePoolSize = corePoolSize;
	this.maximumPoolSize = maximumPoolSize;
	this.keepAliveTime = keepAliveTime;
    }

    /**
     * 处理程序遭到拒绝将抛出运行时
     * @Title: abortPolicyTest void 
     * @author 周俊
     * @date 2018年1月15日 下午1:56:46
     */
    private void abortPolicyTest() {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
        executor.setRejectedExecutionHandler(new MyTaskRejectionStrategy());
        long s = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            final int temp = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("thread_" + temp);
                    System.out.println("当前执行的线程 : " + Thread.currentThread().getName() + "该线程执行后线程池数量 :" + executor.getPoolSize() + "  当前线程执行后线程池中队列数量 :" + executor.getQueue().size());
                }
            });
        }
        executor.shutdown();
        while (!executor.isTerminated());
        System.out.println(System.currentTimeMillis() -s );
    } 
    
    public static void main(String[] args) {
        ThreadPoolTest tpt = new ThreadPoolTest(0,1, 1000);
        tpt.abortPolicyTest();
    }
}
