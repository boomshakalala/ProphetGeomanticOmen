package com.xianzhifengshui.ui.mymaster.mywantedmaster;

import android.os.Handler;

import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我想约的大师列表页控制器
 */
public class MyWantedMasterPresenter extends BasePresenter implements MyWantedMasterContract.Presenter{
    MyWantedMasterContract.View view;

    public MyWantedMasterPresenter(MyWantedMasterContract.View view) {
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
        },3000);
    }

    @Override
    public void loadMore() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.closeWait();
            }
        },3000);
    }
}
