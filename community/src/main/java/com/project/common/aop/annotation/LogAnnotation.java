package com.project.common.aop.annotation;

import java.lang.annotation.*;

/**
 * LogAnnotation
 *
 * @author 
 * @version V1.0
 * @date 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    String action() default "";
}
