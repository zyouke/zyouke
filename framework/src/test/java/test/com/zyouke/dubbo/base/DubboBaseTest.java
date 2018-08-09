package test.com.zyouke.dubbo.base;

import com.alibaba.dubbo.common.compiler.Compiler;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.dubbo.rpc.Protocol;
import com.zyouke.dubbo.base.DynamicCreateObject;
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
    public void dubboRegistryFactoryTest() {
        ExtensionLoader<RegistryFactory> extensionLoader = ExtensionLoader.getExtensionLoader(RegistryFactory.class);
        RegistryFactory registryFactory = extensionLoader.getAdaptiveExtension();
        RegistryFactory zookeeper = extensionLoader.getExtension("zookeeper");
        RegistryFactory redis = extensionLoader.getExtension("redis");
        System.out.println("zookeeperClassName : " + zookeeper.getClass().getName());
        System.out.println("redisClassName : " + redis.getClass().getName());
        Protocol protocolAdaptiveExtension = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    }
    @Test
    public void dubboProtocolTest() {
        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocolAdaptiveExtension = extensionLoader.getAdaptiveExtension();
    }

    @Test
    public void createSimpleClassTest(){
        DynamicCreateObject createObject = new DynamicCreateObject();
        createObject.createSimpleClass("Student");
    }

    @Test
    public void dubboJavassistCompilerTest(){
        ExtensionLoader<com.alibaba.dubbo.common.compiler.Compiler> extensionLoader = ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.common.compiler.Compiler.class);
        Compiler javassistCompiler = extensionLoader.getExtension("javassist");
        Class<?> compileClass = javassistCompiler.compile("package com.alibaba.dubbo.registry;\n" +
                "import com.alibaba.dubbo.common.extension.ExtensionLoader;\n" +
                "public class RegistryFactory$Adpative implements com.alibaba.dubbo.registry.RegistryFactory {\n" +
                "     public com.alibaba.dubbo.registry.Registry getRegistry(com.alibaba.dubbo.common.URL arg0) {\n" +
                "        if (arg0 == null) throw new IllegalArgumentException(\"url == null\");\n" +
                "        com.alibaba.dubbo.common.URL url = arg0;\n" +
                "        String extName = ( url.getProtocol() == null ? \"dubbo\" : url.getProtocol() );\n" +
                "        if(extName == null) throw new IllegalStateException(\"Fail to get extension(com.alibaba.dubbo.registry.RegistryFactory) name from url(\" + url.toString() + \") use keys([protocol])\");\n" +
                "        com.alibaba.dubbo.registry.RegistryFactory extension = (com.alibaba.dubbo.registry.RegistryFactory)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.registry.RegistryFactory.class).getExtension(extName);\n" +
                "        return extension.getRegistry(arg0);\n" +
                "    }\n" +
                "}\n", ExtensionLoader.class.getClassLoader());
        try {
            System.out.println(compileClass);
            RegistryFactory registryFactory = (RegistryFactory)compileClass.newInstance();
            System.out.println(registryFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
