package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class Master implements Serializable{
    private static final long serialVersionUID = 4616237234657569947L;

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
}
