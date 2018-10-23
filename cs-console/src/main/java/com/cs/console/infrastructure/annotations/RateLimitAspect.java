package com.cs.console.infrastructure.annotations;


import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitAspect {

    String name() default "";

    double permitsPerSecond() default 5.0;

}