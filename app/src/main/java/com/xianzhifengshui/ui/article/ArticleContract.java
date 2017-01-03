package com.xianzhifengshui.ui.article;

import com.xianzhifengshui.api.model.Article;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述: 服务项目页mvp接口
 */
public class ArticleContract {
    interface View extends IView<Presenter>{
        void refreshData(List<Article> data);
        void loadMore(List<Article> data);
        void showEmpty();
        void showFailure();
        void closeLoadMore();
    }

    interface Presenter extends IPresenter{
        void refreshData(String masterCode);
        void loadMore(String masterCode);
    }
}
