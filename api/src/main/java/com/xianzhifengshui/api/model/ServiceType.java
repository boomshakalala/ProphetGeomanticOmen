package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/11/1.
 * 描述: 服务项目列表模型
 */
public class ServiceType implements Serializable {
    private static final long serialVersionUID = -5166918877092212963L;

    private String summary; //简介

    private int price; //价格

    private int singleVolume; //成单量

    private double marking; //评分

    private int type; //服务类型

    public void setSummary(String summary){
        this.summary = summary;
    }
    public String getSummary(){
        return this.summary;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }
    public void setSingleVolume(int singleVolume){
        this.singleVolume = singleVolume;
    }
    public int getSingleVolume(){
        return this.singleVolume;
    }
    public void setMarking(double marking){
        this.marking = marking;
    }
    public double getMarking(){
        return this.marking;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
}
