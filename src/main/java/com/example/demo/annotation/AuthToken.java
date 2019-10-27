package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @Author WeLong
 * @create 2019/10/21 16:41
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthToken {
    String[] role_name() default "user";
}
