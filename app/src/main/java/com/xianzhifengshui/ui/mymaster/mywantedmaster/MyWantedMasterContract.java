package com.xianzhifengshui.ui.mymaster.mywantedmaster;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我想约的大师mvp接口
 */
public interface MyWantedMasterContract {

    interface View extends IView<Present>{
        void refreshData(List<String> data);
        void loadMore(List<String> data);
        void showEmpty();
        void showFailure();
    }

    interface Present extends IPresenter{
        void refreshData();
        void loadMore();
    }

}
