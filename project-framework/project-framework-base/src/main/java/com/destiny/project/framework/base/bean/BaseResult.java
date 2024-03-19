package com.destiny.project.framework.base.bean;


import com.destiny.project.framework.base.enums.BaseErrorCodeEnum;
import com.destiny.project.framework.base.enums.CallTypeEnum;

import java.util.Objects;

public class BaseResult extends BaseResponse {
    private boolean success;
    private String code;
    private String message;
    private String sign;
    private CallTypeEnum callType;

    public BaseResult() {
        this.code = BaseErrorCodeEnum.SUCCESS.getCode();
        this.message = BaseErrorCodeEnum.SUCCESS.getDesc();
        this.success = this.checkSuccess();
    }

    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
        this.success = this.checkSuccess();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
        this.success = this.checkSuccess();
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
    }

    public CallTypeEnum getCallType() {
        return this.callType;
    }

    public void setCallType(CallTypeEnum callType) {
        this.callType = callType;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean checkSuccess() {
        return Objects.equals(this.code, BaseErrorCodeEnum.SUCCESS.getCode()) ? Boolean.TRUE : Boolean.FALSE;
    }
}

