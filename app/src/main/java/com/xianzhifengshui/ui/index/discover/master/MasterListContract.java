package com.xianzhifengshui.ui.index.discover.master;

import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 大师列表mvp接口
 */
public interface MasterListContract {
     interface View extends IView<Presenter>{
        void refreshData(ArrayList<Master> data);
        void loadMore(ArrayList<Master> data);
        void showEmpty();
        void showFailure();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }
}
