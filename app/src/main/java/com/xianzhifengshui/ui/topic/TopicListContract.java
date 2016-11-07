package com.xianzhifengshui.ui.topic;

import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/7.
 * 描述:
 */
public interface TopicListContract {
    interface View extends IView<Presenter>{
        void refreshData(List<Topic> data);
        void loadMore(List<Topic> data);
        void showEmpty();
        void showFailure();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }
}
