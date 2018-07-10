package com.zyouke.annotation;

import java.lang.annotation.*;

/**
 * FruitColor.java
 * 水果颜色注解类
 * @author zyouke
 * @create 2017/11/21 10:00
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    /**
     * @Instructions: 颜色枚举
     * @Author: zyouke
     * @Date: 2017/11/21 10:01
     */
    public enum Color{
        BULE,
        RED,
        GREEN
    }

    /**
     * @Instructions: 获取颜色
     * @Author: zyouke
     * @Date: 2017/11/21 10:02
     */
    Color fruitColor() default Color.GREEN;


}
