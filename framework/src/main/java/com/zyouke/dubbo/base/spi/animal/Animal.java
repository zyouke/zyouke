package com.zyouke.dubbo.base.spi.animal;

import com.alibaba.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @Author: zhoujun
 * 测试 dubbo Adaptive注解和spi注解
 */
@SPI
public interface Animal {

    public void eat();

    public void sleep();

    @Adaptive("play")
    public void play(URL url);

}
