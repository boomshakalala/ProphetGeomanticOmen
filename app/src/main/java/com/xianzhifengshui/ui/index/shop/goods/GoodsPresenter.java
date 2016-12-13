package com.xianzhifengshui.ui.index.shop.goods;

import com.xianzhifengshui.api.model.Goods;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

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
        List<Goods> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Goods());
        }
        view.refreshData(data);
    }

    @Override
    public void loadMore() {

    }
}
