package com.xianzhifengshui.ui.index.home;


import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/26.
 * 描述:
 */
public interface HomeContract {
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
        void praise(String masterCode);
    }
}
