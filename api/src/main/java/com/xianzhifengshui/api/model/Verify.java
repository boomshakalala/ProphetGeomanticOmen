package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/12/7.
 * 描述:
 */
public class Verify implements Serializable{
    private static final long serialVersionUID = -5640542780505168711L;

    private String mobilePhone; //手机号码
    private String vcode;//验证码

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
}
