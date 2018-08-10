package com.zyouke.dubbo.base.spi.job;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author: zhoujun
 * 测试 dubbo Activate注解
 */
@SPI
public interface Job {
    public void work();
}
