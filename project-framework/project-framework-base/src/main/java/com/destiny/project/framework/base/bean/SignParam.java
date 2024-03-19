package com.destiny.project.framework.base.bean;

public class SignParam extends BaseParam {
    private String sign;
    private String data;

    public SignParam() {
    }

    public String getSign() {
        return this.sign;
    }

    public String getData() {
        return this.data;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setData(String data) {
        this.data = data;
    }
}

