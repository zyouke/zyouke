package com.service.test.d_20180614;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.zyouke.utils.RandomUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    @org.junit.Test
    public void test1() {
        ApplicationContext applicationContext = getApplicationContext();
        DruidDataSource dataSource = (DruidDataSource) applicationContext.getBean("dataSource");
        try {
            DruidPooledConnection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO t_user(user_name,user_age,create_time) VALUES ('"+ RandomUtil.getRandomString()+"',"+RandomUtil.getRandomNumber(0,100)+",NOW())";
            boolean execute = statement.execute(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @org.junit.Test
    public void test2() {


    }

    @org.junit.Test
    public void test4() {

    }

    @org.junit.Test
    public void test3() {

    }

    private ApplicationContext getApplicationContext(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        return applicationContext;
    }
}