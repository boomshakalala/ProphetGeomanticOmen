package com.xianzhifengshui.ui.search;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: chengx
 * 日期: 2016/10/28.
 * 描述:
 */
public interface SearchContract {
    interface View extends IView<Presenter>{
        void showEmpty();
        void showFailure();
        void loadData();
        void loadMore();
    }

    interface Presenter extends IPresenter{
        void loadData();

        void loadMore();
    }
}
