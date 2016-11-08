package com.xianzhifengshui.ui.article;

import android.os.Handler;

import com.xianzhifengshui.api.model.Article;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.ui.article.ArticleContract;

import java.util.ArrayList;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述:
 */
public class ArticlePresenter extends BasePresenter implements ArticleContract.Presenter {
    ArticleContract.View view;

    public ArticlePresenter(ArticleContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void refreshData() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.closeWait();
                view.showEmpty();
            }
        },1500);
    }

    @Override
    public void loadMore() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.closeWait();
            }
        },1500);
    }
}
