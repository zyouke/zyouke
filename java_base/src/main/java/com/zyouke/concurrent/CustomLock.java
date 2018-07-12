package com.zyouke.concurrent;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 依据ReentrantLock 自定义锁
 */
public class CustomLock extends AbstractQueuedSynchronizer implements Lock, Serializable{

    /**
     *
     * @param expect
     * @param update
     * @param object 无用参数,解决与父类被final修饰的方法同名
     * @return
     */
    public boolean compareAndSetState(int expect, int update,Object object){
        boolean boo = compareAndSetState(expect, update);
        return boo;
    }




    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
