package com.zyouke.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class GamePlayerProxyDynamic implements InvocationHandler {

    private Object targetObject;

    public GamePlayerProxyDynamic(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("当前即将执行的methodName : " + method.getName());
        Object object = method.invoke(targetObject,args);
        System.out.println(method.getName()+" 执行完毕,即将返回结果");
        return object;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),targetObject.getClass().getInterfaces(), this);
    }
}
