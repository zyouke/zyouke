package test.com.zyouke.dubbo.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.RegistryFactory;
import org.junit.Test;

/**
 * @Author: zhoujun
 * dubbo spi 机制的测试
 */
public class DubboSPITest {
    /**
     * @Instructions: 通过Dubbo spi机制获取的class 类
     * @Author: zyouke
     * @Date: 2017/11/27 10:54
     */
    @Test
    public void getClassByDubboSpiTest() {
        ExtensionLoader<RegistryFactory> extensionLoader = ExtensionLoader.getExtensionLoader(RegistryFactory.class);
        RegistryFactory zookeeper = extensionLoader.getExtension("zookeeper");
        RegistryFactory redis = extensionLoader.getExtension("redis");
        System.out.println("zookeeper_name : " + zookeeper.getClass().getName());
        System.out.println("redis_name : " + redis.getClass().getName());
    }


}
