package com.zyouke.distributed_transactions.goods;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderSpringBootApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(OrderSpringBootApplication.class, args);
    }
}
