package com.zyouke.netty;

import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * 在查看EventLoopGroup源码时，相应的测试
 */
public class EventLoopGroupTest {
    /**
     * io.netty.util.concurrent.MultithreadEventExecutorGroup
     * #MultithreadEventExecutorGroup(int, java.util.concurrent.Executor, io.netty.util.concurrent.EventExecutorChooserFactory, java.lang.Object...)
     * 113行-124行
     */
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

    /**
     * io.netty.util.concurrent.DefaultEventExecutorChooserFactory#isPowerOfTwo(int)
     */
    @Test
    public void isPowerOfTwoTest(){
        System.out.println(6&-6);
    }

    /**
     * io.netty.util.concurrent.MultithreadEventExecutorGroup
     * #MultithreadEventExecutorGroup(int, java.util.concurrent.Executor, io.netty.util.concurrent.EventExecutorChooserFactory, java.lang.Object...)
     * 128行
     * Collections.unmodifiableSet 方法将集合转为不可操作的集合，其底层原理是将调用操作集合的方法，直接异常返回
     */
    @Test
    public void unmodifiableSetTest(){
        Set<String> set = new LinkedHashSet<>(10);
        set.add("aa");
        set.add("bb");
        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
        unmodifiableSet.add("cc");
    }
}
