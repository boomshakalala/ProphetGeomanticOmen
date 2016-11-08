package com.xianzhifengshui.ui.index.home;

import android.os.Handler;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者: chengx
 * 日期: 2016/10/26.
 * 描述:
 */
public class HomePresenter extends BasePresenter implements HomeContract.Presenter{

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.closeWait();
            }
        },3000);
    }
}
