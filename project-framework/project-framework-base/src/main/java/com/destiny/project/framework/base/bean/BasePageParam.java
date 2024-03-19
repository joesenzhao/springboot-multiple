package com.destiny.project.framework.base.bean;

import com.destiny.project.framework.base.api.ApiElement;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class BasePageParam extends BaseParam {
    @XStreamOmitField
    @ApiElement("当前页数")
    private int pageNum = 1;
    @XStreamOmitField
    @ApiElement("每页查询数据条数")
    private int pageSize = 10;

    public BasePageParam() {
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
