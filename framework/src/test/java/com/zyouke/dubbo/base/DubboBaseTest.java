package com.zyouke.dubbo.base;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.compiler.Compiler;
import org.apache.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.zyouke.dubbo.base.spi.animal.Animal;
import com.zyouke.dubbo.base.spi.job.Job;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void dubboSPITest() {
        ExtensionLoader<RegistryFactory> extensionLoader = ExtensionLoader.getExtensionLoader(RegistryFactory.class);
        RegistryFactory zookeeper = extensionLoader.getExtension("zookeeper");
        RegistryFactory redis = extensionLoader.getExtension("redis");
        System.out.println("zookeeperClassName : " + zookeeper.getClass().getName());
        System.out.println("redisClassName : " + redis.getClass().getName());
        System.out.println("-------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------");
        ExtensionLoader<Animal> animalExtensionLoader = ExtensionLoader.getExtensionLoader(Animal.class);
        Animal tiger = animalExtensionLoader.getExtension("tiger");
        tiger.eat();
        tiger.sleep();
        System.out.println("-------------------------------------------------------------");
        Animal fish = animalExtensionLoader.getExtension("fish");
        fish.eat();
        fish.sleep();
    }

    @Test
    public void dubboAdaptiveTest() {
        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocolAdaptiveExtension = extensionLoader.getAdaptiveExtension();
        ProxyFactory proxyFactory = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
        System.out.println("-------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------");
        ExtensionLoader<Animal> animalExtensionLoader = ExtensionLoader.getExtensionLoader(Animal.class);
        Animal adaptiveExtension = animalExtensionLoader.getAdaptiveExtension();
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("play","tiger");
        adaptiveExtension.play(new URL("dubbo","127.0.0.1",8080,parameters));
    }

    @Test
    public void dubboActivateTest() {
        ExtensionLoader<Job> jobExtensionLoader = ExtensionLoader.getExtensionLoader(Job.class);
        URL url = new URL("dubbo", "127.0.0.1", 8080);
        //url = url.addParameter(Constants.TOKEN_KEY,"teacher");
        List<Job> jobs = jobExtensionLoader.getActivateExtension(url, Constants.TOKEN_KEY,"job_group");
        for (Job job : jobs) {
            job.work();
        }

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
