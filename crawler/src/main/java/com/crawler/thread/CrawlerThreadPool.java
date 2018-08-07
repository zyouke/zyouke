package com.crawler.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CrawlerThreadPool.java
 * @Description: 爬虫项目自定义线程池
 * @author 周俊
 * @date 2017年8月27日 下午1:58:04
 */
public class CrawlerThreadPool {

    private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

    /**
     * 
     * @Title: newFixedThreadPool
     * @Description: 获取线程执行类ExecutorService
     * @author 周俊
     * @param nThreads
     * @return ExecutorService
     * @throws
     */
    public static ExecutorService newFixedThreadPool(int nThreads) {
	return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, workQueue);
    }

    /**
     * @Title: getQueueSize
     * @Description: 获取当前线程池中线程个数
     * @author 周俊
     * @return int
     * @throws
     */
    public static int getQueueSize() {
	return workQueue.size();
    }
}
