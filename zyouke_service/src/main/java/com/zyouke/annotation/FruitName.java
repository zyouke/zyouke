package com.zyouke.annotation;

import java.lang.annotation.*;

/**
 * FruitName.java
 * 水果名称
 * @author zyouke
 * @create 2017/11/21 10:35
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {

    String fruitName() default "";
}
