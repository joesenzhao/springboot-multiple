package com.destiny.project.framework.base.utils;


import com.destiny.project.framework.base.api.Mask;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.lang.reflect.Field;

public class ToStringUtil extends ReflectionToStringBuilder {
    public ToStringUtil(Object object, ToStringStyle style) {
        super(object, style);
    }

    protected Object getValue(Field field) throws IllegalArgumentException, IllegalAccessException {
        if (field.getType() == String.class && field.isAnnotationPresent(Mask.class)) {
            String v = (String) field.get(this.getObject());
            Mask mask = (Mask) field.getAnnotation(Mask.class);
            switch (mask.type()) {
                case CHINESE_NAME:
                    return mask.isMask() ? MaskUtil.nameMask(v) : v;
                case ID_CARD:
                    return mask.isMask() ? MaskUtil.certMask(v) : v;
                case TEL_PHONE:
                    return mask.isMask() ? MaskUtil.telPhoneMask(v) : v;
                case MOBILE_PHONE:
                    return mask.isMask() ? MaskUtil.mobileMask(v) : v;
                case ADDRESS:
                    return mask.isMask() ? MaskUtil.addrMask(v) : v;
                case EMAIL:
                    return mask.isMask() ? MaskUtil.emailMask(v) : v;
                case BANK_CARD:
                    return mask.isMask() ? MaskUtil.bankCardNoMask(v) : v;
                case BASE64_SPLIT:
                    return mask.isMask() ? MaskUtil.splitBase64(v) : v;
                case MOBILE_MSG_CAPTCHA:
                    return mask.isMask() ? MaskUtil.captchaMask(v) : v;
                case CREDIT_CODE:
                    return mask.isMask() ? MaskUtil.creditCodeMask(v) : v;
                default:
                    return v;
            }
        } else {
            return field.get(this.getObject());
        }
    }
}
