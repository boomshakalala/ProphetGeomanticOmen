package com.xianzhifengshui.ui.mybankcard;

import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;
import com.xianzhifengshui.ui.index.discover.master.MasterListContract;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public interface MyBankCardContract  {
    interface View extends IView<Presenter>{
        void refreshData(List<Topic> data);
        void loadMore(List<Topic> data);
        void showEmpty();
        void showFailure();
        void closeLoadMore();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }
}
