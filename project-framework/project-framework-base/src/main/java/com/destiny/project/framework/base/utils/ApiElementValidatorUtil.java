package com.destiny.project.framework.base.utils;

import com.destiny.project.framework.base.api.ApiElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ApiElementValidatorUtil implements ConstraintValidator<ApiElement, Object> {
    private static final Logger logger = LoggerFactory.getLogger(ApiElementValidatorUtil.class);
    private ApiElement apiElement;

    public ApiElementValidatorUtil() {
    }

    public void initialize(ApiElement alphaNumeric) {
        this.apiElement = alphaNumeric;
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return true;
    }
}

