package com.xianzhifengshui.ui.index.discover.lecture;

import android.os.Handler;

import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.ui.index.discover.master.MasterListContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/12.
 * 描述: 讲座列表页控制器
 */
public class LectureListPresenter extends BasePresenter implements LectureListContract.Presenter{

    private LectureListContract.View view;

    public LectureListPresenter(LectureListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void requestData() {

    }


    @Override
    public void refreshData() {
        final List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
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
        final List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.loadMore(data);
                view.closeWait();
            }
        },3000);
    }
}
