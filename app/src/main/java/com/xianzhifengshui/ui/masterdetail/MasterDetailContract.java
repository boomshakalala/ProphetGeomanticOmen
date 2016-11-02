package com.xianzhifengshui.ui.masterdetail;

import com.xianzhifengshui.api.model.Evaluate;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.ServiceType;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/1.
 * 描述:
 */
public class MasterDetailContract {

    interface View extends IView<Presenter>{
        void showInfo(Master master);
        void showService(List<ServiceType> serviceTypes);
        void showAboutMaster(String desc);
        void showArticle();
        void showEvaluate(List<Evaluate> evaluates);
    }

    interface Presenter extends IPresenter{
        void requestData();
    }

}
