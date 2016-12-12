package com.xianzhifengshui.ui.index.shop.goods;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：
 */

public class GoodsPresenter extends BasePresenter implements GoodsContract.Presenter {
    GoodsContract.View view;

    public GoodsPresenter(GoodsContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void loadMore() {

    }
}
