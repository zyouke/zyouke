package test.com.zyouke.concurrent;

import com.zyouke.concurrent.CustomLock;
import com.zyouke.threadPool.CustomThreadPool;
import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.junit.Test;

import javax.xml.transform.Source;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhoujun
 * 对自定义锁测试,设计到对aqs(AbstractQueuedSynchronizer)机制的测试
 */
public class CustomLockTest {
    static int num = 0;
    @Test
    public void compareAndSetStateTest(){
        final CustomLock customLock = new CustomLock();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        CustomThreadPool customThreadPool = new CustomThreadPool(10,10);
        for (int i = 0; i < 10; i++) {
            customThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (!customLock.compareAndSetState(0, 1, new Object())){}
                    try {
                        countDownLatch.await();
                        num = num + 1;
                        System.out.println(ThreadUtil.getThreadName() + "----------------- : " + num);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    customLock.release(1);
                }
            });
        }
        countDownLatch.countDown();
        while (!customThreadPool.isTerminated()){}
    }




}
