package test.com.zyouke.concurrent;

import com.zyouke.threadPool.CustomThreadPool;
import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 测试
 */
public class ReentrantLockTest {
    private  Lock lock = new ReentrantLock(true);
    public  void createOrder(String userCode){
        // 加锁
        lock.lock();
        System.out.println("用户"+userCode+"正在创建的订单号:"+ UUID.randomUUID().toString());
        lock.unlock();
    }

    @Test
    public void createOrderTest(){
        CustomThreadPool customThreadPool = new CustomThreadPool();
        for (int i = 0; i < 100; i++) {
            customThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    createOrder(RandomUtil.getRandomString());
                }
            });
        }
        while (!customThreadPool.isTerminated()){}
    }

}
