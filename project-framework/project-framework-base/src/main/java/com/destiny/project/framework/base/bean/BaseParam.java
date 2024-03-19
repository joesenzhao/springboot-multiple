package com.destiny.project.framework.base.bean;

import com.destiny.project.framework.base.api.ApiElement;
import com.destiny.project.framework.base.enums.CallTypeEnum;
import com.destiny.project.framework.base.utils.UUIDUtil;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public abstract class BaseParam extends BaseRequest {
    @XStreamOmitField
    @ApiElement("统一流水号gid")
    private String gid;

    @XStreamOmitField
    private long timestamp;

    @XStreamOmitField
    private CallTypeEnum callType;

    public BaseParam() {
        this.callType = CallTypeEnum.REAL;
        this.gid = UUIDUtil.getUuid();
        this.timestamp = System.currentTimeMillis();
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public CallTypeEnum getCallType() {
        return this.callType;
    }

    public void setCallType(CallTypeEnum callType) {
        this.callType = callType;
    }
}
