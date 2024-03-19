package com.destiny.project.framework.base.utils;

import com.alibaba.fastjson.JSONObject;
import com.destiny.project.framework.base.api.ApiElement;
import com.destiny.project.framework.base.bean.BaseResult;
import com.destiny.project.framework.base.enums.BaseErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ValidationUtil {
    private static final Logger log = LoggerFactory.getLogger(ValidationUtil.class);
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public ValidationUtil() {
    }

    public static <T> boolean validateEntity(T obj, BaseResult baseResult) {
        boolean suc = false;

        try {
            if (null == obj) {
                return !suc;
            }

            if (!validateOnClass(obj, baseResult)) {
                return suc;
            }

            initFields(obj);
            suc = validate(obj, baseResult);
        } catch (Exception var4) {
            log.error("参数校验是出错", var4);
            baseResult.setCode(BaseErrorCodeEnum.PARAM_ERROR.getCode());
            baseResult.setMessage(var4.getCause().getMessage());
        }

        return suc;
    }

    private static <T> void initFields(T obj) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field[] var3 = fields;
        int var4 = fields.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];
            boolean fieldHasAnno = field.isAnnotationPresent(ApiElement.class);
            if (fieldHasAnno) {
                ApiElement apiElementFiled = (ApiElement) field.getAnnotation(ApiElement.class);
                InvocationHandler h = Proxy.getInvocationHandler(apiElementFiled);
                Field hField = h.getClass().getDeclaredField("memberValues");
                hField.setAccessible(true);
                Map memberValues = (Map) hField.get(h);
                Type type = field.getGenericType();
                if (type instanceof ParameterizedType) {
                    type = ((ParameterizedType) type).getRawType();
                }

                memberValues.put("classType", type);
                String[] dependents = (String[]) ((String[]) memberValues.get("dependents"));
                if (dependents.length > 0) {
                    for (int i = 0; i < dependents.length; ++i) {
                        Field dependFile = clazz.getDeclaredField(dependents[i]);
                        dependFile.setAccessible(true);
                        Object object = dependFile.get(obj);
                        if (null == object) {
                            memberValues.put("dependentNeed", false);
                            break;
                        }

                        memberValues.put("dependentNeed", true);
                    }
                }
            }
        }

    }

    private static <T, M> boolean validateOnClass(T obj, M baseResult) throws NoSuchFieldException, IllegalAccessException {
        boolean result = true;
        Class clazz = obj.getClass();
        ApiElement apiElementClass = (ApiElement) clazz.getAnnotation(ApiElement.class);
        if (!StringUtil.isBlank(apiElementClass)) {
            InvocationHandler h = Proxy.getInvocationHandler(apiElementClass);
            Field hField1 = h.getClass().getDeclaredField("memberValues");
            hField1.setAccessible(true);
            Map map = (Map) hField1.get(h);
            String[] str = (String[]) ((String[]) map.get("AtLeastOne"));
            if (str.length > 0) {
                result = false;

                for (int i = 0; i < str.length; ++i) {
                    Field dependFile = clazz.getDeclaredField(str[i]);
                    dependFile.setAccessible(true);
                    Object object = dependFile.get(obj);
                    if (null != object && !"".equals(object)) {
                        result = true;
                        break;
                    }
                }

                if (!result) {
                    if (BaseResult.class.isAssignableFrom(baseResult.getClass())) {
                        ((BaseResult) baseResult).setCode(BaseErrorCodeEnum.FAIL.getCode());
                        ((BaseResult) baseResult).setMessage("字段" + JSONObject.toJSONString(str) + "至少存在一个值");
                    } else {
                        ((BaseResult) baseResult).setCode(BaseErrorCodeEnum.FAIL.getCode());
                        ((BaseResult) baseResult).setMessage("字段" + JSONObject.toJSONString(str) + "至少存在一个值");
                    }
                }
            }
        }

        return result;
    }

    private static <T, M> boolean validate(T obj, M baseResponse) {
        Set<ConstraintViolation<T>> set = validator.validate(obj, new Class[]{Default.class});
        if (null != set && !set.isEmpty()) {
            Iterator var3 = set.iterator();

            while (var3.hasNext()) {
                ConstraintViolation<T> cv = (ConstraintViolation) var3.next();
                String[] results = cv.getMessage().split("&");
                if (2 == results.length) {
                    String message;
                    if (BaseResult.class.isAssignableFrom(baseResponse.getClass())) {
                        ((BaseResult) baseResponse).setCode(results[1]);
                        message = results[0].contains(BaseErrorCodeEnum.getByCode(results[1]).getDesc()) ? results[0] : results[0] + ":" + BaseErrorCodeEnum.getByCode(results[1]).getDesc();
                        ((BaseResult) baseResponse).setMessage(message);
                        ((BaseResult) baseResponse).setSuccess(false);
                    } else {
                        ((BaseResult) baseResponse).setCode(results[1]);
                        message = results[0].contains(BaseErrorCodeEnum.getByCode(results[1]).getDesc()) ? results[0] : results[0] + ":" + BaseErrorCodeEnum.getByCode(results[1]).getDesc();
                        ((BaseResult) baseResponse).setMessage(message);
                    }

                    return false;
                }
            }
        }

        return true;
    }
}

