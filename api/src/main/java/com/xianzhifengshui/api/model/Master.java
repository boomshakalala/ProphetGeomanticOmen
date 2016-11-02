package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述: 大师列表模型
 */
public class Master implements Serializable{
    private static final long serialVersionUID = 4616237234657569947L;

    private String desc; //大师详细介绍

    private int appoint; //预约数

    private String pic; //大师图片

    private String type; //服务类型

    private int collecteStatus; //收藏状态

    private String title; //大师标语

    private String address; //大师地址

    private String summary; //大师简介

    private String icon; //大师头像

    private int level; //大师等级

    private String nickname; //大师昵称

    private String name; //大师名称

    private int singleVolume; //成单量

    private int collection; //收藏量

    private int pointOfPraise; //点赞数

    private String masterCode; //大师编号（唯一性）

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSingleVolume() {
        return singleVolume;
    }

    public void setSingleVolume(int singleVolume) {
        this.singleVolume = singleVolume;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getPointOfPraise() {
        return pointOfPraise;
    }

    public void setPointOfPraise(int pointOfPraise) {
        this.pointOfPraise = pointOfPraise;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAppoint() {
        return appoint;
    }

    public void setAppoint(int appoint) {
        this.appoint = appoint;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCollecteStatus() {
        return collecteStatus;
    }

    public void setCollecteStatus(int collecteStatus) {
        this.collecteStatus = collecteStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
