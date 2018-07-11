package test.com.zyouke.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author: zhoujun
 */
public class JvmThreadPoolTest {

    @Test
    public void test1(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue(100));
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("-------------------"+Thread.currentThread().getName());
                }
            });
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()){}
    }

    @Test
    public void test2(){
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(100);
        try {
            queue.put("aaaaa");
            queue.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
