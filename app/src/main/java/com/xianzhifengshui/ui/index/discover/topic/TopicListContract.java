package com.xianzhifengshui.ui.index.discover.topic;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 话题列表mvp接口
 */
public interface TopicListContract {
     interface View extends IView<Presenter>{
        void refreshData(List<String> data);
        void loadMore(List<String> data);
        void showEmpty();
        void showFailure();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }
}
