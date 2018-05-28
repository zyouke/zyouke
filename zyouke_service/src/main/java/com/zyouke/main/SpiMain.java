package com.zyouke.main;

import com.zyouke.jdk.spi.IHelloSpi;

import java.util.ServiceLoader;

/**
 * SpiMain.java
 *
 * @author zyouke
 * @create 2017/11/26 21:57
 */
public class SpiMain {
    public static void main(String[] args){
        ServiceLoader<IHelloSpi> serviceLoader = ServiceLoader.load(IHelloSpi.class);
        for (IHelloSpi helloSpi : serviceLoader){
                helloSpi.print();
        }
    }
}
