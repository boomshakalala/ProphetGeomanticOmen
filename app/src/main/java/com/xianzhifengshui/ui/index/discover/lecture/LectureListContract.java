package com.xianzhifengshui.ui.index.discover.lecture;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: 陈冠希
 * 日期: 2016/10/11.
 * 描述: 讲座列表mvp接口
 */
public interface LectureListContract {
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
