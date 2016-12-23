package com.xianzhifengshui.api.net;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 作者：chengx
 * 日期：2016/12/20
 * 描述：
 */

public class Response<T> implements Serializable{
    private static final long serialVersionUID = -6401869905868406915L;
    String json;
    int code;
    ActionCallbackListener<T> callback;
    Type typeOfClass;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ActionCallbackListener<T> getCallback() {
        return callback;
    }

    public void setCallback(ActionCallbackListener<T> callback) {
        this.callback = callback;
    }
}
