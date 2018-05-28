package com.zyouke.annotation;

/**
 * Apple.java
 * 用于测试的类
 * @author zyouke
 * @create 2017/11/21 10:03
 */
public class Apple {
    @FruitColor(fruitColor= FruitColor.Color.RED)
    private String appleColor;

    @FruitName(fruitName = "苹果")
    private String appleName;
}
