package com.zyouke.distributed_transactions.goods;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class OrderSpringBootApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(OrderSpringBootApplication.class, args);
    }
}
