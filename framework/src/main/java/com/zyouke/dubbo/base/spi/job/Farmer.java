package com.zyouke.dubbo.base.spi.job;

import com.alibaba.dubbo.common.extension.Activate;

/**
 * @Author: zhoujun
 */
@Activate(group = {"job_group"},order = 2)
public class Farmer implements Job{
    @Override
    public void work() {
        System.out.println("农民在田间劳作.......");
    }
}
