package test.com.zyouke.thread;

import com.zyouke.threadPool.CustomThreadPool;
import com.zyouke.utils.ThreadUtil;
import org.junit.Test;

/**
 * 线程池测试
 * @Author: zhoujun
 */
public class CustomThreadPoolTest {

    @Test
    public void test1(){
        CustomThreadPool customThreadPool = new CustomThreadPool();
        for (int i = 0; i < 1000; i++) {
            customThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    ThreadUtil.sleep(300);
                    System.out.println("-----------------------"+Thread.currentThread().getName());
                }
            });
        }
        while (!customThreadPool.isTerminated()){}
    }
}
