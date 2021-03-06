package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者：chengx
 * 日期：2016/12/27
 * 描述：
 */

public class PayOrder implements Serializable {
    private static final long serialVersionUID = -1221319360919611390L;
    String tradeNo; //订单编号
    String prepayId;//预支付ID	支付使用
    String sign;//签名

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    @Override
    public String toString() {
        return "PayOrder{" +
                "tradeNo='" + tradeNo + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
