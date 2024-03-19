package com.destiny.project.framework.base.api;

import com.destiny.project.framework.base.enums.MaskTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Mask {
    MaskTypeEnum type();

    boolean isMask() default true;
}