package com.zyouke.spring.tx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TransactionTest {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OrderService orderService;

    @Test
    public void jdbcTemplateTest(){
        System.out.println(jdbcTemplate);
    }

    @Test
    public void addOrderTest(){
        orderService.addOrder();
    }
}
