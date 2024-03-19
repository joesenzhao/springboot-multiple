package com.destiny.project.framework.base.api;


import com.destiny.project.framework.base.utils.ApiElementValidatorUtil;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {ApiElementValidatorUtil.class}
)
@Documented
public @interface ApiElement {
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class classType() default String.class;

    boolean dependentNeed() default true;

    boolean unique() default false;

    /**
     * @deprecated
     */
    @Deprecated
    String message() default "";

    String value() default "";

    int maxLen() default 2147483647;

    int minLen() default 0;

    boolean required() default false;

    Class enumClass() default Enum.class;

    String[] dependents() default {};

    String[] AtLeastOne() default {};

    String regular() default "";

    String selectParamCode() default "";
}

