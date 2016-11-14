package com.xianzhifengshui.ui.mytopic.myattentiontopic;

import android.os.Handler;

import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/9.
 * 描述:
 */
public class MyAttentionTopicPresenter extends BasePresenter implements MyAttentionTopicContract.Presenter{

    private MyAttentionTopicContract.View view;

    public MyAttentionTopicPresenter(MyAttentionTopicContract.View view) {
        this.view = view;
    }

    @Override
    public void refreshData() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Topic> data = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    data.add(new Topic());
                }
                view.refreshData(data);
                view.closeWait();
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
