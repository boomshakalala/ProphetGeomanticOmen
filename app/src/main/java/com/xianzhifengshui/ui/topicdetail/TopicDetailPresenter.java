package com.xianzhifengshui.ui.topicdetail;

import android.os.Handler;

import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/7.
 * 描述:
 */
public class TopicDetailPresenter extends BasePresenter implements TopicDetailContract.Presenter {

    private TopicDetailContract.View view;

    public TopicDetailPresenter(TopicDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void refreshData() {
        final List<Object> data = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            data.add("");
        }
        if (view.isActive())
            view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.refreshData(data);
                view.closeWait();
            }
        },3000);
    }

    @Override
    public void loadMore() {
        final List<Object> data = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            data.add("");
        }
        if (view.isActive())
            view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.loadMore(data);
                view.closeWait();
            }
        },3000);
    }

}
