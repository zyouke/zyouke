package test.com.zyouke.thread;

import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.junit.Test;

/**
 * @Author: zhoujun
 */
public class ThreadTest {

    @Test
    public void test1(){
        MyThread myThread = new MyThread();
        myThread.start();
        while (true){
            System.out.println("---------" + myThread.getState());
            ThreadUtil.sleep(1000);
        }
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            while (true){
                if (RandomUtil.getRandomNumber(0,10) == 5){
                    throw new RuntimeException();
                }
            }
        }
    }




}
