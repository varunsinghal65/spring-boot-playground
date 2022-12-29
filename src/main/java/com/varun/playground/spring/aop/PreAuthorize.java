package com.varun.playground.spring.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuthorize {
    String userName() default "";

    String userAction() default "";
}