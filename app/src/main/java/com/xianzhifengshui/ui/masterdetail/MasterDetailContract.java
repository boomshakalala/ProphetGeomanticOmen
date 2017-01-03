package com.xianzhifengshui.ui.masterdetail;

import android.view.ViewOutlineProvider;

import com.xianzhifengshui.api.model.Article;
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
        void showArticle(List<Article> articles);
        void showEvaluate(List<Evaluate> evaluates);
        void closeMoreService();
        void closeMoreEvaluate();
        void closeMoreArticle();
        void showCollect();
        void showUnCollect();
    }

    interface Presenter extends IPresenter{
        void requestData();
        void collectMaster(String masterCode);
        void unCollectMaster(String masterCode);
    }

}
