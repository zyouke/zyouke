package test.com.zyouke.dubbo.service;

import com.zyouke.bean.Area;
import com.zyouke.dubbo.service.AreaServiceImpl;
import com.zyouke.service.IAreaService;
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
        IAreaService areaService = (IAreaService) context.getBean("areaService");
        List<Area> list = areaService.findList(0, 100);
        System.out.println("------------------ size : "+list.size());
    }


}
