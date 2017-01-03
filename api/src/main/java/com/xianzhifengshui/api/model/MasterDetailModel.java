package com.xianzhifengshui.api.model;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/1.
 * 描述: 大师详情页模型
 */
public class MasterDetailModel extends Master {

    private static final long serialVersionUID = 8513107300364273847L;

    private List<Evaluate> evaluateList;
    private List<ServiceType> serviceType;
    private List<Article> articleList;

    public List<Evaluate> getEvaluateList() {
        return evaluateList;
    }

    public void setEvaluateList(List<Evaluate> evaluateList) {
        this.evaluateList = evaluateList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<ServiceType> getServiceType() {
        return serviceType;
    }

    public void setServiceType(List<ServiceType> serviceType) {
        this.serviceType = serviceType;
    }
}
