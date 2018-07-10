package test.com.zyouke.concurrent;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 测试
 */
public class ReentrantLockTest {
    private static Lock lock = new ReentrantLock();
    public static void createOrder(String userCode){
        // 加锁
        lock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("用户"+userCode+"正在创建的订单号:"+ UUID.randomUUID().toString());
        lock.unlock();
    }

    @Test
    public void createOrderTest(){

    }

}
