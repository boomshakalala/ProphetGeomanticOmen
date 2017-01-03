package com.xianzhifengshui.ui.article;

import android.os.Handler;

import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Article;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.ui.article.ArticleContract;
import com.xianzhifengshui.utils.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述:
 */
public class ArticlePresenter extends BasePresenter implements ArticleContract.Presenter {
    ArticleContract.View view;
    int currentPage = 1;

    public ArticlePresenter(ArticleContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void requestData(String masterCode) {
        view.showWaiting();
        api.masterArticleList(masterCode,currentPage, AppConfig.PAGE_SIZE, new ActionCallbackListener<BaseListModel<ArrayList<Article>>>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {
            }

            @Override
            public void onSuccess(BaseListModel<ArrayList<Article>> data) {
                log(data);
                view.closeWait();
                if (data.getPageNum()==currentPage){
                    //关闭记载更多
                    view.closeLoadMore();
                }
                ArrayList<Article> dataList = data.getList();
                if (dataList != null && dataList.size()>0) {
                    if (currentPage == 1){
                        view.refreshData(dataList);
                    }else {
                        view.loadMore(dataList);
                    }
                }else {
                    if (currentPage == 1){
                        view.showEmpty();
                    }else {
                        view.showTip("没有更多了");
                    }
                }
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.closeWait();
                if (currentPage == 1){
                    view.showFailure();
                }
                KLog.d(getClass().getSimpleName(),message);
                view.showTip(message);
            }
        });
    }

    @Override
    public void refreshData(String masterCode) {
        currentPage = 1;
        requestData(masterCode);
    }

    @Override
    public void loadMore(String masterCode) {
       currentPage++;
        requestData(masterCode);
    }
}
