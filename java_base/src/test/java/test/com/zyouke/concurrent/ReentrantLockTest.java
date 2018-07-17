package test.com.zyouke.concurrent;

import com.zyouke.threadPool.CustomThreadPool;
import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 测试
 */
public class ReentrantLockTest {
    private  Lock lock = new ReentrantLock();
    public  void createOrder(String threadName){
        // 加锁
        lock.lock();
        System.out.println("线程"+threadName+"正在创建的订单号:"+ UUID.randomUUID().toString());
        lock.unlock();
    }

    @Test
    public void createOrderTest1(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        CustomThreadPool customThreadPool = new CustomThreadPool(10,10);
        for (int i = 0; i < 10; i++) {
            customThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    createOrder(ThreadUtil.getThreadName());
                }
            });
        }
        countDownLatch.countDown();
        while (!customThreadPool.isTerminated()){}
    }
    @Test
    public void createOrderTest2(){

    }

}
