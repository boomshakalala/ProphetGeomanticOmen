package com.xianzhifengshui.ui.recharge;

import android.content.Context;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述:
 */
public class RechargePresenter extends BasePresenter implements RechargeContract.Presenter {

    RechargeContract.View view;

    public RechargePresenter(RechargeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void pay(int payMethod) {

    }
}
