package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public class Bill implements Serializable {
    private static final long serialVersionUID = -8383384200608349252L;

    private String body;

    private String deviceInfo;

    private String feeType;

    private String notifyUrl;

    private int orderStatus;

    private String outTradeNo;

    private String prepayId;

    private String spbillCreateIp;

    private int totalFee;

    private String tradeType;

    private String userCode;

    private int xid;

    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return this.body;
    }
    public void setDeviceInfo(String deviceInfo){
        this.deviceInfo = deviceInfo;
    }
    public String getDeviceInfo(){
        return this.deviceInfo;
    }
    public void setFeeType(String feeType){
        this.feeType = feeType;
    }
    public String getFeeType(){
        return this.feeType;
    }
    public void setNotifyUrl(String notifyUrl){
        this.notifyUrl = notifyUrl;
    }
    public String getNotifyUrl(){
        return this.notifyUrl;
    }
    public void setOrderStatus(int orderStatus){
        this.orderStatus = orderStatus;
    }
    public int getOrderStatus(){
        return this.orderStatus;
    }
    public void setOutTradeNo(String outTradeNo){
        this.outTradeNo = outTradeNo;
    }
    public String getOutTradeNo(){
        return this.outTradeNo;
    }
    public void setPrepayId(String prepayId){
        this.prepayId = prepayId;
    }
    public String getPrepayId(){
        return this.prepayId;
    }
    public void setSpbillCreateIp(String spbillCreateIp){
        this.spbillCreateIp = spbillCreateIp;
    }
    public String getSpbillCreateIp(){
        return this.spbillCreateIp;
    }
    public void setTotalFee(int totalFee){
        this.totalFee = totalFee;
    }
    public int getTotalFee(){
        return this.totalFee;
    }
    public void setTradeType(String tradeType){
        this.tradeType = tradeType;
    }
    public String getTradeType(){
        return this.tradeType;
    }
    public void setUserCode(String userCode){
        this.userCode = userCode;
    }
    public String getUserCode(){
        return this.userCode;
    }
    public void setXid(int xid){
        this.xid = xid;
    }
    public int getXid(){
        return this.xid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "body='" + body + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", feeType='" + feeType + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", orderStatus=" + orderStatus +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", spbillCreateIp='" + spbillCreateIp + '\'' +
                ", totalFee=" + totalFee +
                ", tradeType='" + tradeType + '\'' +
                ", userCode='" + userCode + '\'' +
                ", xid=" + xid +
                '}';
    }
}
