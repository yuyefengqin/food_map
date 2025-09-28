package com.gok.food_map.log.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    //执行的操作描述
    String operation() default "";
    //业务类型
    String businessType() default "";
    // 是否忽略请求参数
    boolean ignoreParam() default false;
}
