package com.xianzhifengshui.ui.index.discover.topictype;

import android.os.Handler;

import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/12.
 * 描述: 讲座列表页控制器
 */
public class TopicTypeListPresenter extends BasePresenter implements TopicTypeListContract.Presenter{

    private TopicTypeListContract.View view;

    public TopicTypeListPresenter(TopicTypeListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void requestData() {

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
        },3000);

    }

    @Override
    public void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.closeWait();
            }
        },3000);
    }
}
