package com.cs.console.infrastructure.annotations;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitAspect {

    @AliasFor(
            annotation = RateLimitAspect.class
    )
    String name() default "";

}