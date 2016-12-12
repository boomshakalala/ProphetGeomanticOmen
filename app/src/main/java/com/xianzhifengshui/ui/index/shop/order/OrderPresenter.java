package com.xianzhifengshui.ui.index.shop.order;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：
 */

public class OrderPresenter extends BasePresenter implements OrderContract.Presenter {
    OrderContract.View view;

    public OrderPresenter(OrderContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
}
