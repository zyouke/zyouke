package com.zyouke.spi.impl;

import com.zyouke.spi.IHelloSpi;

/**
 * HelloSpiTwoImpl.java
 *
 * @author zyouke
 * @create 2017/11/26 21:53
 */
public class HelloSpiTwoImpl implements IHelloSpi {
    @Override
    public void print() {
        System.out.println("这是spi第二个实现类");
    }
}
