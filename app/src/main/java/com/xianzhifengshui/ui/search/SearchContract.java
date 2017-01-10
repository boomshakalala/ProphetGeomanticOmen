package com.xianzhifengshui.ui.search;

import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.ArrayList;

/**
 * 作者: chengx
 * 日期: 2016/10/28.
 * 描述:
 */
public interface SearchContract {
    interface View extends IView<Presenter>{
        void showEmpty();
        void showFailure(String message);
        void loadData(ArrayList<Master> dataList);
        void loadMore(ArrayList<Master> dataList);
        void showInit();
        void setKeyword(String keyword);
        void closeLoadMore();
    }

    interface Presenter extends IPresenter{
        void loadData(String keyword);

        void loadMore();
    }
}
