package com.xianzhifengshui.ui.evaluate;

import com.xianzhifengshui.api.model.Evaluate;
import com.xianzhifengshui.api.model.ServiceType;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述: 服务项目页mvp接口
 */
public class EvaluateContract {
    interface View extends IView<Presenter>{
        void refreshData(List<Evaluate> data);
        void loadMore(List<Evaluate> data);
        void closeLoadMore();
        void showEmpty();
        void showFailure();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }
}
