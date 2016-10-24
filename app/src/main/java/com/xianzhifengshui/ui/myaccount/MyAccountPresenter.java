package com.xianzhifengshui.ui.myaccount;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述: 我的账户页控制器
 */
public class MyAccountPresenter extends BasePresenter implements MyAccountContract.Presenter{

    MyAccountContract.View view;

    public MyAccountPresenter(MyAccountContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void requestAccountInfo() {

    }
}
