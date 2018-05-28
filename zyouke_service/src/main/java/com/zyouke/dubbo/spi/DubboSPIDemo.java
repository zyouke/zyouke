package com.zyouke.dubbo.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.RegistryFactory;

/**
 * DubboSPIDemo.java
 * duboo spi 机制的实现解析
 * @author zyouke
 * @create 2017/11/27 10:47
 */
public class DubboSPIDemo {

    /**
     * @Instructions: 获取Dubbo spi机制产生的class 类
     * @Author: zyouke
     * @Date: 2017/11/27 10:54
     */
    public static void getDubboSpiClass(){
        ExtensionLoader<RegistryFactory> extensionLoader = ExtensionLoader.getExtensionLoader(RegistryFactory.class);
        RegistryFactory zookeeper = extensionLoader.getExtension("zookeeper");
        RegistryFactory redis = extensionLoader.getExtension("redis");
        System.out.println("zookeeper : " + zookeeper.hashCode());
        System.out.println("redis : " + redis.hashCode());
    }
}
