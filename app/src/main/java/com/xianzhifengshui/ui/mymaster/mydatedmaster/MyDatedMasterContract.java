package com.xianzhifengshui.ui.mymaster.mydatedmaster;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我想约的大师mvp接口
 */
interface MyDatedMasterContract {

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
