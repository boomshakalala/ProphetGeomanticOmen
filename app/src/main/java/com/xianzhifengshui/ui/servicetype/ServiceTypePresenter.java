package com.xianzhifengshui.ui.servicetype;

import android.os.Handler;

import com.xianzhifengshui.api.model.ServiceType;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述:
 */
public class ServiceTypePresenter extends BasePresenter implements ServiceTypeContract.Presenter {
    ServiceTypeContract.View view;

    public ServiceTypePresenter(ServiceTypeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void refreshData() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<ServiceType> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new ServiceType());
                }
                view.refreshData(list);
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
                ArrayList<ServiceType> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new ServiceType());
                }
                view.loadMore(list);
                view.closeWait();
            }
        },1500);
    }
}
