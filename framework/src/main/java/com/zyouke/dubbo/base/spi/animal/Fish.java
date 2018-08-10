package com.zyouke.dubbo.base.spi.animal;

import com.alibaba.dubbo.common.URL;
import com.zyouke.dubbo.base.spi.animal.Animal;

/**
 * @Author: zhoujun
 */
public class Fish implements Animal {
    @Override
    public void eat() {
        System.out.println("鱼吃草...");
    }

    @Override
    public void sleep() {
        System.out.println("鱼睡在水里...");
    }
    @Override
    public void play(URL url) {
        System.out.println("鱼无忧无虑...");
    }
}
