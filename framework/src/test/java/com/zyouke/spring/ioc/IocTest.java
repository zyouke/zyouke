package com.zyouke.spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoujun
 */
public class IocTest {

    @Test
    public void iocTest1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ioc.xml");
        IocTestBean iocTestBean = (IocTestBean) applicationContext.getBean("iocTestBean");
        iocTestBean.setName("aaa");
        System.out.println(iocTestBean.getName());
    }
}
