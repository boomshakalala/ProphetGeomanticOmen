package com.xianzhifengshui.ui.index.shop.order;

import com.xianzhifengshui.api.model.Order;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void refreshData() {
        List<Order> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Order());
        }
        view.refreshData(data);
    }

    @Override
    public void loadMore() {

    }
}
