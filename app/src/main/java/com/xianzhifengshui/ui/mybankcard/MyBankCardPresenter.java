package com.xianzhifengshui.ui.mybankcard;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public  class MyBankCardPresenter extends BasePresenter implements MyBankCardContract.Presenter {

    MyBankCardContract.View view;

    public MyBankCardPresenter(MyBankCardContract.View view) {
        this.view = view;
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void loadMore() {

    }
}
