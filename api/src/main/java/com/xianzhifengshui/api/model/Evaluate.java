package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/11/1.
 * 描述: 评论列表模型
 */
public class Evaluate implements Serializable {
    private static final long serialVersionUID = -7624042723084984100L;

    private String content; //客户评价内容

    private String icon; //客户头像

    private String title; //客户简介

    private String nickname; //客户昵称

    private String date; //客户评价日期

    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    public String getIcon(){
        return this.icon;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return this.nickname;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
}
