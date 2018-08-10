package com.zyouke.dubbo.base.spi.job;

/**
 * @Author: zhoujun
 */
public class Farmer implements Job{
    @Override
    public void work() {
        System.out.println("农民在田间劳作.......");
    }
}
