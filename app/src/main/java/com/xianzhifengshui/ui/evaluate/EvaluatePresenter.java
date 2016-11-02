package com.xianzhifengshui.ui.evaluate;

import android.os.Handler;

import com.xianzhifengshui.api.model.Evaluate;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.ui.evaluate.EvaluateContract;

import java.util.ArrayList;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述:
 */
public class EvaluatePresenter extends BasePresenter implements EvaluateContract.Presenter {
    EvaluateContract.View view;

    public EvaluatePresenter(EvaluateContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void refreshData() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Evaluate> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new Evaluate());
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
                ArrayList<Evaluate> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new Evaluate());
                }
                view.loadMore(list);
                view.closeWait();
            }
        },1500);
    }
}
