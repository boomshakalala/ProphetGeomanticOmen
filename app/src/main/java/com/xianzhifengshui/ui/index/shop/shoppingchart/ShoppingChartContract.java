package com.xianzhifengshui.ui.index.shop.shoppingchart;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：
 */

public interface ShoppingChartContract {
    interface View extends IView<Presenter>{
        void refreshData(List<Object> data);
        void loadMore(List<Object> data);
        void showEmpty();
        void showFailure();
        void closeLoadMore();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }
}
