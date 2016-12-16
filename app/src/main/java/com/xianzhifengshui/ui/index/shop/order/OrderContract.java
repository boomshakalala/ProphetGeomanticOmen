package com.xianzhifengshui.ui.index.shop.order;

import com.xianzhifengshui.api.model.Goods;
import com.xianzhifengshui.api.model.Order;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：
 */

public interface OrderContract {
    interface View extends IView<Presenter>{
        void refreshData(List<Order> data);
        void loadMore(List<Order> data);
        void showEmpty();
        void showFailure();
        void closeLoadMore();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }
}
