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
public class ServiceTest {

    @Test
    public void areaServiceTest(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        IDubboService dubboService = (IDubboService) context.getBean("dubboService");
        System.out.println("IDubboService 的代理对象的class类型 : " + dubboService.getClass());
        CustomThreadPool customThreadPool = new CustomThreadPool(1000,1000);
        for (int i = 0; i < 1000; i++) {
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
        ThreadUtil.sleep(5000);
    }


}
