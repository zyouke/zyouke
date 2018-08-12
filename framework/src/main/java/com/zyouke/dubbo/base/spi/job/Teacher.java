package com.zyouke.dubbo.base.spi.job;

import com.alibaba.dubbo.common.extension.Activate;

/**
 * @Author: zhoujun
 */
@Activate(group = {"job_group"},order = 1,value = "teacher")
public class Teacher implements Job{
    @Override
    public void work() {
        System.out.println("老师在上课.....");
    }
}
