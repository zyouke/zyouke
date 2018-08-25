package com.zyouke.dubbo.base.spi.job;

import com.alibaba.dubbo.common.extension.Activate;

/**
 * @Author: zhoujun
 */
@Activate(group = {"job_group"})
public class Computer implements Job{
    @Override
    public void work() {
        System.out.println("程序员在敲代码.....");
    }
}
