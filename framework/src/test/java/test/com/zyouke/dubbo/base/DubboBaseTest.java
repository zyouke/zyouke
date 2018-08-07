package test.com.zyouke.dubbo.base;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.RegistryFactory;
import org.junit.Test;

/**
 * @Author: zhoujun
 * dubbo 基础的测试
 * spi 机制
 * 动态编译
 */
public class DubboBaseTest {
    /**
     * @Instructions: 通过Dubbo spi机制获取的class 类
     * @Author: zyouke
     * @Date: 2017/11/27 10:54
     */
    @Test
    public void dubboSpiTest() {
        ExtensionLoader<RegistryFactory> extensionLoader = ExtensionLoader.getExtensionLoader(RegistryFactory.class);
        RegistryFactory registryFactory = extensionLoader.getAdaptiveExtension();
        RegistryFactory zookeeper = extensionLoader.getExtension("zookeeper");
        RegistryFactory redis = extensionLoader.getExtension("redis");
        System.out.println("zookeeperClassName : " + zookeeper.getClass().getName());
        System.out.println("redisClassName : " + redis.getClass().getName());
    }
}
