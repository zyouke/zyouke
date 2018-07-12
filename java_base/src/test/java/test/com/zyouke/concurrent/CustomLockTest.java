package test.com.zyouke.concurrent;

import com.zyouke.concurrent.CustomLock;
import com.zyouke.threadPool.CustomThreadPool;
import org.junit.Test;

/**
 * @Author: zhoujun
 * 对自定义锁测试,设计到对aqs(AbstractQueuedSynchronizer)机制的测试
 */
public class CustomLockTest {

    @Test
    public void compareAndSetStateTest(){
        final CustomLock customLock = new CustomLock();
        CustomThreadPool customThreadPool = new CustomThreadPool(5,10);
        for (int i = 0; i < 10; i++) {
            customThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (!customLock.compareAndSetState(0, 1, new Object())){}
                    System.out.println("-------------" + Thread.currentThread().getName());
                    customLock.release(1);
                }
            });
        }
        while (!customThreadPool.isTerminated()){}
    }



}
