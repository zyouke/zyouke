package com.zyouke.dubbo.base;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.IOException;

/**
 * 使用javassist 动态创建类
 */
public class DynamicCreateObject {

    public void createSimpleClass(String className){
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass(className);
        try {
            Class clazz = ctClass.toClass();
            System.out.println("--------------" + clazz.getName());
            ctClass.writeFile("E://temp//javassist");
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
