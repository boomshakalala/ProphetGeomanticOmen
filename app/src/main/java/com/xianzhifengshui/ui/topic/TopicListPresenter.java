package com.xianzhifengshui.ui.topic;

import android.os.Handler;

import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.ui.index.home.HomeContract;
import com.xianzhifengshui.utils.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/7.
 * 描述:
 */
public class TopicListPresenter extends BasePresenter implements TopicListContract.Presenter {

    private TopicListContract.View view;
    private int currentPage = 1;

    public TopicListPresenter(TopicListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    private void requestData(){
        view.showWaiting();
        api.topicList(currentPage, AppConfig.PAGE_SIZE, new ActionCallbackListener<BaseListModel<ArrayList<Topic>>>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                KLog.d(getClass().getSimpleName(),"bytesWritten="+bytesWritten+"totalSize="+totalSize);
            }

            @Override
            public void onSuccess(BaseListModel<ArrayList<Topic>> data) {
                if (data.getPageNum()==currentPage){
                    //关闭记载更多
                    view.closeLoadMore();
                }
                ArrayList<Topic> dataList = data.getList();
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
                view.closeWait();
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
    public void refreshData() {
        currentPage = 1;
        requestData();
    }

    @Override
    public void loadMore() {
        currentPage++;
        requestData();
    }
}
