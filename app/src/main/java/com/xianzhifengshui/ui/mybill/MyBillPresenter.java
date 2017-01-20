package com.xianzhifengshui.ui.mybill;

import android.content.Context;
import android.os.Handler;

import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Bill;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public class MyBillPresenter extends BasePresenter implements MyBillContract.Presenter{

    private MyBillContract.View view;
    private int currentPage = 1;

    public MyBillPresenter(MyBillContract.View view) {
        this.view = view;
    }


    private void requestData(){
        if (!isLogin()){
            view.toLoginActivity((Context)view);
        }
        String userCode = getUserCode();
        view.showWaiting();
        api.payOrderList(userCode, currentPage, AppConfig.PAGE_SIZE, new ActionCallbackListener<BaseListModel<ArrayList<Bill>>>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(BaseListModel<ArrayList<Bill>> data) {
                if (data.getPageNum()==currentPage){
                    //关闭记载更多
                    view.closeLoadMore();
                }
                ArrayList<Bill> dataList = data.getList();
                ArrayList<Object> totalList = new ArrayList<>();
                
                totalList.addAll(dataList);
                if (dataList != null && dataList.size()>0) {
                    if (currentPage == 1){
                        view.refreshData(totalList);
                    }else {
                        view.loadMore(totalList);
                    }
                }else {
                    if (currentPage == 1){
                        view.showEmpty();
                    }else {
                        view.showTip("没有更多了");
                    }
                }
                view.closeWait();
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.closeWait();
                if (currentPage == 1){
                    view.showFailure();
                }
                KLog.d(getClass().getSimpleName(),message);
                view.showTip(message);
            }
        });
    }

    @Override
    public void refreshData() {
        currentPage = 1;
        requestData();
    }

    @Override
    public void loadMore() {
        currentPage++;
        requestData();
    }
}
