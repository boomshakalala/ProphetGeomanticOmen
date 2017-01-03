package com.xianzhifengshui.ui.servicetype;

import android.os.Handler;

import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.model.ServiceType;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述:
 */
public class ServiceTypePresenter extends BasePresenter implements ServiceTypeContract.Presenter {
    ServiceTypeContract.View view;
    private int currentPage = 1;

    public ServiceTypePresenter(ServiceTypeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void requestData() {
        view.showWaiting();
//        api.lecturesList(currentPage, AppConfig.PAGE_SIZE, new ActionCallbackListener<BaseListModel<ArrayList<Lecture>>>() {
//            @Override
//            public void onProgress(long bytesWritten, long totalSize) {
//            }
//
//            @Override
//            public void onSuccess(BaseListModel<ArrayList<Lecture>> data) {
//                log(data);
//                view.closeWait();
//                if (data.getPageNum()==currentPage){
//                    //关闭记载更多
//                    view.closeLoadMore();
//                }
//                ArrayList<Lecture> dataList = data.getList();
//                if (dataList != null && dataList.size()>0) {
//                    if (currentPage == 1){
//                        view.refreshData(dataList);
//                    }else {
//                        view.loadMore(dataList);
//                    }
//                }else {
//                    if (currentPage == 1){
//                        view.showEmpty();
//                    }else {
//                        view.showTip("没有更多了");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(int errorEvent, String message) {
//                view.closeWait();
//                if (currentPage == 1){
//                    view.showFailure();
//                }
//                KLog.d(getClass().getSimpleName(),message);
//                view.showTip(message);
//            }
//        });
    }


    @Override
    public void refreshData() {

    }

    @Override
    public void loadMore() {

    }
}
