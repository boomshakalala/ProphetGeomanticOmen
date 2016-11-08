package com.xianzhifengshui.ui.index.discover.topictype;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 话题列表mvp接口
 */
public interface TopicTypeListContract {
     interface View extends IView<Presenter>{
         void refreshData(List<String> data);
         void loadMore(List<String> data);
         void showEmpty();
         void showFailure();
         void closeLoadMore();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }
}
