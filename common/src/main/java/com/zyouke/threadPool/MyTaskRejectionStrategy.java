package com.zyouke.threadPool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyTaskRejectionStrategy implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
	System.out.println("这是自定义的任务拒绝策略..........");
    }

}
