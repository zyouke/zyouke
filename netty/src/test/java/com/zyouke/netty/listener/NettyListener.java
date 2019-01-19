package com.zyouke.netty.listener;

import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class NettyListener {

    @Test
    public void nettyListenerTest(){
        EventExecutor eventExecutor = new UnorderedThreadPoolEventExecutor(2);
        Future<String> nettyFuture = eventExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "成功";
            }
        });
        FutureListener<Object> futureListener = new FutureListener<Object>() {
            @Override
            public void operationComplete(Future<Object> future) throws Exception {
                System.out.println("NettyListener被执行。。。。。。");
            }
        };
        nettyFuture.addListener(futureListener);
        try {
            String result = nettyFuture.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
