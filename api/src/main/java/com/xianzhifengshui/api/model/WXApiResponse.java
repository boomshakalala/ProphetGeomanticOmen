package com.xianzhifengshui.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/12/6.
 * 描述:
 */
public class WXApiResponse implements Serializable{
    private static final long serialVersionUID = 1142140265215794757L;
    private String access_token; //接口调用凭证
    private String expires_in;//access_token接口调用凭证超时时间，单位（秒）
    private String refresh_token;//用户刷新access_token
    private String openid;//授权用户唯一标识
    private String scope;//用户授权的作用域，使用逗号（,）分隔
    private int errcode;//错误码
    private String errmsg;//错误信息
    private String nickname;//普通用户昵称
    private int sex;//普通用户性别，1为男性，2为女性
    private String province;//普通用户个人资料填写的省份
    private String city;//普通用户个人资料填写的城市
    private String country;//国家，如中国为CN
    private String headimgurl;//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    private List<String> privilege;//用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
    private String unionid;//用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "\nWXApiResponse{" +"\n"+
                "access_token='" + access_token + "\'\n" +
                ", expires_in='" + expires_in + "\'\n" +
                ", refresh_token='" + refresh_token + "\'\n" +
                ", openid='" + openid + "\'\n" +
                ", scope='" + scope + "\'\n" +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + "\'\n" +
                ", nickname='" + nickname + "\'\n" +
                ", sex=" + sex +
                ", province='" + province + "\'\n" +
                ", city='" + city + "\'\n" +
                ", country='" + country + "\'\n" +
                ", headimgurl='" + headimgurl + "\'\n" +
                ", privilege=" + privilege +
                ", unionid='" + unionid + "\'\n" +
                '}';
    }
}
