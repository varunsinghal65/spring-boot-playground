package com.varun.playground.springAop;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String param1() default "";
    String param2() default "";
}