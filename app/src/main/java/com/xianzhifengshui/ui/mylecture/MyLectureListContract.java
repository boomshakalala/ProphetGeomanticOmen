package com.xianzhifengshui.ui.mylecture;

import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 讲座列表mvp接口
 */
public interface MyLectureListContract {
     interface View extends IView<Presenter>{
         void refreshData(List<Lecture> data);
         void loadMore(List<Lecture> data);
         void showEmpty();
         void showFailure();
         void closeLoadMore();
    }

    interface Presenter extends IPresenter{
        void refreshData(int type);
        void loadMore();
    }
}
