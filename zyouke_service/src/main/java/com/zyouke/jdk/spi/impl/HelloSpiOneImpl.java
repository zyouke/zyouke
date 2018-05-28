package com.zyouke.jdk.spi.impl;

import com.zyouke.jdk.spi.IHelloSpi;

/**
 * HelloSpiOneImpl.java
 *
 * @author zyouke
 * @create 2017/11/26 21:51
 */
public class HelloSpiOneImpl implements IHelloSpi{
    @Override
    public void print() {
        System.out.println("这是spi第一个实现类");
    }
}
