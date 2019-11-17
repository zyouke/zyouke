package com.zyouke.distributed_transactions.goods;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class  OrderSpringBootApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(OrderSpringBootApplication.class, args);
    }
}
