package com.zyouke.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional
    public void addOrder(){
        jdbcTemplate.execute("INSERT INTO `t_order` (`uid`,`order_num`) VALUES (1,'"+ UUID.randomUUID().toString()+"')");
        int i = 1/0;
    }




}
