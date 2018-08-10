package com.zyouke.dubbo.base.spi;

import com.alibaba.dubbo.common.URL;

/**
 * @Author: zhoujun
 */
public class Tiger implements Animal{
    @Override
    public void eat() {
        System.out.println("老虎吃其他动物肉...");
    }

    @Override
    public void sleep() {
        System.out.println("老虎睡在山洞里...");
    }

    @Override
    public void play(URL url) {
        System.out.println("老虎无所事事...");
    }

}
