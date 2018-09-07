package test.com.zyouke.dubbo.service;

import com.zyouke.bean.Area;
import com.zyouke.service.IDubboService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: zhoujun
 */
public class ServiceTest {

    @Test
    public void areaServiceTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        IDubboService dubboService = (IDubboService) context.getBean("dubboService");
        System.out.println("IDubboService 的代理对象的class类型 : " + dubboService.getClass());
        for (int i = 0; i < 1000; i++) {
            System.out.println("------------------"+dubboService.getExecuteThread());
        }
    }


}
