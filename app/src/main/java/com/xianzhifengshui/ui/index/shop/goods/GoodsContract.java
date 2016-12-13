package com.xianzhifengshui.ui.index.shop.goods;

import com.xianzhifengshui.api.model.Goods;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：商城列表mvp借口
 */

public interface GoodsContract {
    interface View extends IView<Presenter>{
        void refreshData(List<Goods> data);
        void loadMore(List<Goods> data);
        void showEmpty();
        void showFailure();
        void closeLoadMore();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }

}
