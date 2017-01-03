package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: 陈冠希
 * 日期: 2016/10/8.
 * 描述: 用户模型
 */
public class User implements Serializable{
    private static final long serialVersionUID = 9043766419151746508L;

    private String accessType;

    private int type;

    private String accessToken;

    private int id;

    private long createTime; //注册时间

    private String username; //用户名

    private int isDel; //是否被删除

    private String mobilePhone; //手机号

    private String bizCode;

    private String creater;

    private String lastUpdater;

    private int isLock;

    private long lastModifyTime;

    private String password;

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setLastModifyTime(long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setCreateTime(int createTime){
        this.createTime = createTime;
    }
    public long getCreateTime(){
        return this.createTime;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setIsDel(int isDel){
        this.isDel = isDel;
    }
    public int getIsDel(){
        return this.isDel;
    }
    public void setMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
    }
    public String getMobilePhone(){
        return this.mobilePhone;
    }
    public void setBizCode(String bizCode){
        this.bizCode = bizCode;
    }
    public String getBizCode(){
        return this.bizCode;
    }
    public void setCreater(String creater){
        this.creater = creater;
    }
    public String getCreater(){
        return this.creater;
    }
    public void setLastUpdater(String lastUpdater){
        this.lastUpdater = lastUpdater;
    }
    public String getLastUpdater(){
        return this.lastUpdater;
    }
    public void setIsLock(int isLock){
        this.isLock = isLock;
    }
    public int getIsLock(){
        return this.isLock;
    }
    public void setLastModifyTime(int lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }
    public long getLastModifyTime(){
        return this.lastModifyTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", username='" + username + '\'' +
                ", isDel=" + isDel +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", bizCode='" + bizCode + '\'' +
                ", creater='" + creater + '\'' +
                ", lastUpdater='" + lastUpdater + '\'' +
                ", isLock=" + isLock +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }
}
