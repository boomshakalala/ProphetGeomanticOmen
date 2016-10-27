package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class City implements Serializable {
    private static final long serialVersionUID = -3307216570609055698L;

    private String bizCode;
    private String cityCode;
    private String cityName;
    private String id;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "bizCode='" + bizCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
