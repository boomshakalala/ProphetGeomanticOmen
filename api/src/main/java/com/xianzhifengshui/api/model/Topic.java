package com.xianzhifengshui.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/7.
 * 描述:
 */
public class Topic implements Serializable{
    private static final long serialVersionUID = -6363785208894689458L;

    private String content;

    private String title;

    private String icon;

    private String nickname;

    private String issuer;

    private int forward;

    private String userCode;

    private String issueTime;

    private String topicCode;

    private int pointOfPraise;

    private int comment;

    private String type;

    private List<String> photo ;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getForward() {
        return forward;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public int getPointOfPraise() {
        return pointOfPraise;
    }

    public void setPointOfPraise(int pointOfPraise) {
        this.pointOfPraise = pointOfPraise;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }
}
