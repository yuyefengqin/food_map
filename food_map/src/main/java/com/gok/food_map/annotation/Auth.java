package com.gok.food_map.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 定义一个名为Auth的注解 用于标注需要授权的方法或类型
@Target({ElementType.METHOD, ElementType.TYPE})// @Target 指定该注解可以应用于方法或类型的元素上
@Retention(RetentionPolicy.RUNTIME)//  @Retention 指定该注解在运行时是可见的
public @interface Auth {
//    设置是否需要授权验证  默认为true需要  false则不需要
    boolean require() default true;
}