package com.xianzhifengshui.ui.mymaster.mydatedmaster;

import android.os.Handler;

import com.xianzhifengshui.base.BasePresenter;

import org.w3c.dom.ProcessingInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我约过的大师列表页控制器
 */
public class MyDatedMasterPresenter extends BasePresenter implements MyDatedMasterContract.Presenter{

    private MyDatedMasterContract.View view;

    public MyDatedMasterPresenter(MyDatedMasterContract.View view) {
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
