package com.xianzhifengshui.api.model;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class NaviMenu implements Serializable{
    private static final long serialVersionUID = 2318157046840432940L;

    private int id;

    private int isDel;

    private int sort;

    private String remark;

    private String name;

    private String value;

    private String bizCode;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setIsDel(int isDel){
        this.isDel = isDel;
    }
    public int getIsDel(){
        return this.isDel;
    }
    public void setSort(int sort){
        this.sort = sort;
    }
    public int getSort(){
        return this.sort;
    }
    public void setRemark(String remark){
        this.remark = remark;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public void setBizCode(String bizCode){
        this.bizCode = bizCode;
    }
    public String getBizCode(){
        return this.bizCode;
    }

    @Override
    public String toString() {
        return "NaviMenu{" +
                "id=" + id +
                ", isDel=" + isDel +
                ", sort=" + sort +
                ", remark='" + remark + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", bizCode='" + bizCode + '\'' +
                '}';
    }
}
