package com.xianzhifengshui.ui.index.shop.shoppingchart;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：
 */

public class ShoppingChartPresenter extends BasePresenter implements ShoppingChartContract.Presenter {

    ShoppingChartContract.View view;

    public ShoppingChartPresenter(ShoppingChartContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
}
