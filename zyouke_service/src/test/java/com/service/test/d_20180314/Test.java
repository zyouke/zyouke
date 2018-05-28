package com.service.test.d_20180314;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Test {

    @org.junit.Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @org.junit.Test
    public void test2(){
        System.out.println(new Date().getTime());
    }
}