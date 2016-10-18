package com.xianzhifengshui.ui.mymaster.mywantedmaster;

import android.os.Handler;

import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我想约的大师列表页控制器
 */
public class MyWantedMasterPresent extends BasePresenter implements MyWantedMasterContract.Present{
    MyWantedMasterContract.View view;

    public MyWantedMasterPresent(MyWantedMasterContract.View view) {
        this.view = view;
        view.setPresenter(this);
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
}
