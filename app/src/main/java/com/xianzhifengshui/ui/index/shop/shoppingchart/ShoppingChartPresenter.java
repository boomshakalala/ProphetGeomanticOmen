package com.xianzhifengshui.ui.index.shop.shoppingchart;

import com.xianzhifengshui.adapter.ViewSupportModel;
import com.xianzhifengshui.api.model.Goods;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void refreshData() {
        List<Object> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i==0){
                data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"",false));
            }else {
                data.add(new Goods());
            }
        }
        view.refreshData(data);
    }

    @Override
    public void loadMore() {

    }
}
