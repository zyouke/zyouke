package test.com.zyouke.dubbo.service;

import com.zyouke.bean.Area;
import com.zyouke.service.IDubboService;
import com.zyouke.threadPool.CustomThreadPool;
import com.zyouke.utils.ThreadUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zhoujun
 */
public class DubboServiceTest {

    private static int THREADPOOL_SIZE = 1000;

    @Test
    public void dubboServiceTest(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        IDubboService dubboService = (IDubboService) context.getBean("dubboService");
        IDubboService dubboService20880 = (IDubboService) context.getBean("dubboService20880");
        IDubboService dubboService20881 = (IDubboService) context.getBean("dubboService20881");
        System.out.println("IDubboService 的代理对象的class类型 : " + dubboService.getClass());
        dubboService20880.resetCounter();
        dubboService20881.resetCounter();
        CustomThreadPool customThreadPool = new CustomThreadPool(THREADPOOL_SIZE,THREADPOOL_SIZE);

        for (int i = 0; i < THREADPOOL_SIZE; i++) {
            customThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("------------------"+dubboService.getExecuteThread());
                }
            });
        }
        countDownLatch.countDown();
        ThreadUtil.sleep(3000);
        System.out.println("----------" + dubboService20880.getRequestCount());
        System.out.println("----------" + dubboService20881.getRequestCount());
    }


}
