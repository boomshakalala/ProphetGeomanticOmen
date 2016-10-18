package com.xianzhifengshui.base;

import com.xianzhifengshui.api.Api;
import com.xianzhifengshui.api.ApiImpl;
import com.xianzhifengshui.utils.SPUtils;

/**
 * 作者: chengx
 * 日期: 2016/9/29.
 * 描述: Presenter基类
 */
public class BasePresenter {
    protected Api api;

    public BasePresenter() {
        this.api = new ApiImpl();
    }
}
