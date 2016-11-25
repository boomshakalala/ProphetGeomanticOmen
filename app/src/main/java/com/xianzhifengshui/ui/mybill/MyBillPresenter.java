package com.xianzhifengshui.ui.mybill;

import android.os.Handler;

import com.xianzhifengshui.api.model.Bill;
import com.xianzhifengshui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public class MyBillPresenter extends BasePresenter implements MyBillContract.Presenter{

    MyBillContract.View view;

    public MyBillPresenter(MyBillContract.View view) {
        this.view = view;
    }

    @Override
    public void refreshData() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Object> data = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    if (i==1 || i==5){
                        data.add("");
                    }else {
                        data.add(new Bill());
                    }
                }
                view.refreshData(data);
                view.closeWait();
            }
        },1500);
    }

    @Override
    public void loadMore() {

    }
}
