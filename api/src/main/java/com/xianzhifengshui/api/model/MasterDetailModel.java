package com.xianzhifengshui.api.model;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/1.
 * 描述: 大师详情页模型
 */
public class MasterDetailModel extends Master {

    private static final long serialVersionUID = 8513107300364273847L;

    private List<Evaluate> evaluate;
    private List<ServiceType> serviceType ;

    public List<Evaluate> getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(List<Evaluate> evaluate) {
        this.evaluate = evaluate;
    }

    public List<ServiceType> getServiceType() {
        return serviceType;
    }

    public void setServiceType(List<ServiceType> serviceType) {
        this.serviceType = serviceType;
    }
}
