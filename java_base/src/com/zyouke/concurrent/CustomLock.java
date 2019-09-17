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
    public boolean tryRelease(int arg) {
        if (arg > 0){
            setState(0);
            return true;
        }
        return false;
    }

    public void lock(){

    }

    public void lockInterruptibly() throws InterruptedException{

    }

    public boolean tryLock(){
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException{
        return false;
    }

    public void unlock(){

    }

    public Condition newCondition(){
        return null;
    }
}
