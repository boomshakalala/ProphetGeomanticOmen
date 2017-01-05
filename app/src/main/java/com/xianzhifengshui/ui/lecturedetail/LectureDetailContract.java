package com.xianzhifengshui.ui.lecturedetail;

import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: chengx
 * 日期: 2016/11/3.
 * 描述:
 */
public interface LectureDetailContract {
    interface View extends IView<Presenter>{
        void loadData(Lecture lecture);
        void showFailure();
        void showCollect();
        void showUnCollect();
    }

    interface Presenter extends IPresenter{
        void requestData(String lecturesCode,String userCode);
        void collectMaster(String masterCode);
        void unCollectMaster(String masterCode);
    }
}
