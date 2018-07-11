package com.zyouke.memory;

import org.springframework.stereotype.Component;

/**
 * MemoryDemo.java
 *
 * @author zyouke
 * @create 2018/3/9 16:08
 */
@Component("memoryDemo")
public class MemoryDemo {
    public static String staticMemoryDemoName = "MemoryDemo_static";
    public String notStaticMemoryDemoName = "MemoryDemo";
    static {
        System.out.println("静态代码块执行.......");
    }
    {
        System.out.println("构造代码块执行.......");
    }
    public MemoryDemo() {
        System.out.println("构造方法执行.......");
    }
}
