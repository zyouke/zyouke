package com.zyouke.dubbo.base.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author: zhoujun
 * 动物
 */
@SPI
public interface Animal {

    public void eat();

    public void sleep();

    @Adaptive("play")
    public void play(URL url);

}
