package com.xianzhifengshui.base;

import com.xianzhifengshui.api.Api;
import com.xianzhifengshui.api.ApiImpl;
import com.xianzhifengshui.utils.SPUtils;
import com.xianzhifengshui.utils.ThreadPoolUtils;

/**
 * 作者: chengx
 * 日期: 2016/9/29.
 * 描述: Presenter基类
 */
public class BasePresenter {
    protected Api api;
    protected ThreadPoolUtils threadPool;

    public BasePresenter() {
        this.api = ApiImpl.getInstance();
        this.threadPool = ThreadPoolUtils.getInstance(ThreadPoolUtils.Type.FixedThread,5);
    }
}
