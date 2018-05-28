package com.zyouke.spring.ioc;

import org.springframework.stereotype.Component;

/**
 * IocTestBean.java
 * 用于测试ioc
 * @author zyouke
 * @create 2018/3/14 16:35
 */
@Component
public class IocTestBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
