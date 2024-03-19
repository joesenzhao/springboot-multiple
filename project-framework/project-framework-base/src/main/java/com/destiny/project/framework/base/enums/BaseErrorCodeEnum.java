package com.destiny.project.framework.base.enums;

import java.util.ArrayList;
import java.util.List;

public enum BaseErrorCodeEnum implements EnumBase {
    SYS_ERROR("99999", "系统异常"),
    SUCCESS("00000", "成功"),
    FAIL("00001", "失败"),
    PARAM_ERROR("00002", "参数校验错误"),
    FIELD_IS_REQUIRED("00003", "字段必输"),
    ILLEGAL_ARG("00004", "非法参数"),
    DATA_DOES_NOT_EXIST("00005", "数据不存在"),
    DATA_HAS_EXIST("00006", "数据已存在"),
    OUTER_SYS_HAPPENS_ERROR("50000", "外部系统发生错误"),
    NETWORK_TIMEOUT("50001", "网络超时");

    private final String code;
    private final String message;

    private BaseErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.message;
    }

    public static BaseErrorCodeEnum getByCode(String code) {
        BaseErrorCodeEnum[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            BaseErrorCodeEnum _enum = var1[var3];
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }

        return null;
    }

    public List<BaseErrorCodeEnum> getAllEnum() {
        List<BaseErrorCodeEnum> list = new ArrayList();
        BaseErrorCodeEnum[] var2 = values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            BaseErrorCodeEnum _enum = var2[var4];
            list.add(_enum);
        }

        return list;
    }

    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList();
        BaseErrorCodeEnum[] var2 = values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            BaseErrorCodeEnum _enum = var2[var4];
            list.add(_enum.code);
        }

        return list;
    }
}

