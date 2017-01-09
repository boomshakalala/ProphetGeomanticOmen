package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者：chengx
 * 日期：2017/1/9
 * 描述：
 */

public class UploadResult implements Serializable {
    private static final long serialVersionUID = -3489288115819440865L;
    int code;
    String msg;
    String url;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
