package test.com.zyouke.dubbo.service;

import com.alibaba.dubbo.common.bytecode.Proxy;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;
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
        Proxy bean = (Proxy) context.getBean("areaService");
        System.out.println("IAreaService 的代理对象的class类型 : " + bean.getClass());
        List<Area> list = areaService.findList(0, 100);
        System.out.println("------------------"+list.toString());
    }


}
