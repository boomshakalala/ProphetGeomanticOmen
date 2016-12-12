package com.xianzhifengshui.ui.index.shop.collection;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：商品收藏Presenter
 */

public class CollectionPresenter extends BasePresenter implements CollectionContract.Presenter {
    CollectionContract.View view;

    public CollectionPresenter(CollectionContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
}
