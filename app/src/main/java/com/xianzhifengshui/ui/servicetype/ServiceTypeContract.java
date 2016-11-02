package com.xianzhifengshui.ui.servicetype;

import com.xianzhifengshui.api.model.Master;
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
public class ServiceTypeContract {
    interface View extends IView<Presenter>{
        void refreshData(List<ServiceType> data);
        void loadMore(List<ServiceType> data);
        void showEmpty();
        void showFailure();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void loadMore();
    }
}
