package com.xianzhifengshui.ui.mytopic.myattentiontopic;

import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/9.
 * 描述:
 */
public class MyAttentionTopicContract {
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
