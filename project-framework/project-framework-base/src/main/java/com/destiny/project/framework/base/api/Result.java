package com.destiny.project.framework.base.api;


import com.destiny.project.framework.base.bean.BaseResult;
import com.destiny.project.framework.base.enums.BaseErrorCodeEnum;
import com.destiny.project.framework.base.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> extends BaseResult {
    private T data;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(String code, String message) {
        super(code, message);
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public static Result buildSuccess() {
        return new Result(BaseErrorCodeEnum.SUCCESS.getCode(), "成功");
    }

    public static Result buildSuccess(String msg) {
        return new Result(BaseErrorCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result buildSuccess(T data, String msg) {
        return new Result(BaseErrorCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result buildSuccess(T data) {
        return new Result(BaseErrorCodeEnum.SUCCESS.getCode(), "成功", data);
    }

    public static Result buildFail() {
        return new Result(BaseErrorCodeEnum.FAIL.getCode(), "失败");
    }

    public static Result buildFail(String message) {
        return new Result(BaseErrorCodeEnum.FAIL.getCode(), message);
    }

    public static Result buildFail(String code, String message) {
        return new Result(code, message);
    }

    public Result(BaseErrorCodeEnum baseErrorCodeEnum, String message) {
        super(baseErrorCodeEnum.getCode(), StringUtil.isNotBlank(message) ? message : baseErrorCodeEnum.getDesc());
    }
}