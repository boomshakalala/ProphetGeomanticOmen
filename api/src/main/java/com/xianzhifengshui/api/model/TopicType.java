package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者：chengx
 * 日期：2016/12/23
 * 描述：
 */
public class TopicType implements Serializable{
    private static final long serialVersionUID = 7804129586192422719L;
    String title;
    String typeCode;
    String onFocus;
    String browse;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getOnFocus() {
        return onFocus;
    }

    public void setOnFocus(String onFocus) {
        this.onFocus = onFocus;
    }

    public String getBrowse() {
        return browse;
    }

    public void setBrowse(String browse) {
        this.browse = browse;
    }
}
