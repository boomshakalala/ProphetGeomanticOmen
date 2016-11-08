package com.xianzhifengshui.ui.mymaster.mywantedmaster;

import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我想约的大师mvp接口
 */
public interface MyWantedMasterContract {

    interface View extends IView<Presenter>{
        void refreshData(List<Master> data);
        void loadMore(List<Master> data);
        void showEmpty();
        void showFailure();
        void closeLoadMore();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }

}
