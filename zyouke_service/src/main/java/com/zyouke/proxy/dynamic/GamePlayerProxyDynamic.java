package com.zyouke.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

public class GamePlayerProxyDynamic implements InvocationHandler {

    private Object targetObject;

    public GamePlayerProxyDynamic(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(targetObject,args);
    }
}
