package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述: 讲座列表模型
 */
public class Lecture implements Serializable{
    private static final long serialVersionUID = -2552907353356990352L;

    private int signUp;

    private String pic;

    private int pointOfPraise;

    private int collecteStatus;

    private String sellPrice; //价格

    private String masterDesc;//大师描述

    private String desc;

    private String appoint;

    private String masterCode;

    private String collection;

    private String pointPraise;

    private String startTime;

    private String masterTitle;

    private String masterName;

    private String title;

    private String totalSeats;

    private String duration;

    private String price;

    private String lecturesCode;

    private String remainSeats;

    private String address;

    private String masterIcon;

    public int getSignUp() {
        return signUp;
    }

    public void setSignUp(int signUp) {
        this.signUp = signUp;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPointOfPraise() {
        return pointOfPraise;
    }

    public void setPointOfPraise(int pointOfPraise) {
        this.pointOfPraise = pointOfPraise;
    }

    public int getCollecteStatus() {
        return collecteStatus;
    }

    public void setCollecteStatus(int collecteStatus) {
        this.collecteStatus = collecteStatus;
    }

    public void setSellPrice(String sellPrice){
        this.sellPrice = sellPrice;
    }

    public String getSellPrice(){
        return this.sellPrice;
    }

    public void setMasterDesc(String masterDesc){
        this.masterDesc = masterDesc;
    }

    public String getMasterDesc(){
        return this.masterDesc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return this.desc;
    }

    public void setAppoint(String appoint){
        this.appoint = appoint;
    }

    public String getAppoint(){
        return this.appoint;
    }

    public void setMasterCode(String masterCode){
        this.masterCode = masterCode;
    }

    public String getMasterCode(){
        return this.masterCode;
    }

    public void setCollection(String collection){
        this.collection = collection;
    }

    public String getCollection(){
        return this.collection;
    }

    public void setPointPraise(String pointPraise){
        this.pointPraise = pointPraise;
    }

    public String getPointPraise(){
        return this.pointPraise;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public void setMasterTitle(String masterTitle){
        this.masterTitle = masterTitle;
    }

    public String getMasterTitle(){
        return this.masterTitle;
    }

    public void setMasterName(String masterName){
        this.masterName = masterName;
    }

    public String getMasterName(){
        return this.masterName;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTotalSeats(String totalSeats){
        this.totalSeats = totalSeats;
    }

    public String getTotalSeats(){
        return this.totalSeats;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }

    public String getDuration(){
        return this.duration;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getPrice(){
        return this.price;
    }

    public void setLecturesCode(String lecturesCode){
        this.lecturesCode = lecturesCode;
    }

    public String getLecturesCode(){
        return this.lecturesCode;
    }

    public void setRemainSeats(String remainSeats){
        this.remainSeats = remainSeats;
    }

    public String getRemainSeats(){
        return this.remainSeats;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setMasterIcon(String masterIcon){
        this.masterIcon = masterIcon;
    }

    public String getMasterIcon(){
        return this.masterIcon;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "signUp=" + signUp +
                ", pic='" + pic + '\'' +
                ", pointOfPraise=" + pointOfPraise +
                ", collecteStatus=" + collecteStatus +
                ", sellPrice='" + sellPrice + '\'' +
                ", masterDesc='" + masterDesc + '\'' +
                ", desc='" + desc + '\'' +
                ", appoint='" + appoint + '\'' +
                ", masterCode='" + masterCode + '\'' +
                ", collection='" + collection + '\'' +
                ", pointPraise='" + pointPraise + '\'' +
                ", startTime='" + startTime + '\'' +
                ", masterTitle='" + masterTitle + '\'' +
                ", masterName='" + masterName + '\'' +
                ", title='" + title + '\'' +
                ", totalSeats='" + totalSeats + '\'' +
                ", duration='" + duration + '\'' +
                ", price='" + price + '\'' +
                ", lecturesCode='" + lecturesCode + '\'' +
                ", remainSeats='" + remainSeats + '\'' +
                ", address='" + address + '\'' +
                ", masterIcon='" + masterIcon + '\'' +
                '}';
    }
}
