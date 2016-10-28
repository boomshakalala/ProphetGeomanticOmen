package com.xianzhifengshui.ui.index.discover.master;

import android.os.Handler;

import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.ConstUtils;
import com.xianzhifengshui.utils.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/12.
 * 描述: 大师列表页控制器
 */
public class MasterListPresenter extends BasePresenter implements MasterListContract.Presenter{

    private MasterListContract.View view;
    private int currentPage = 1;

    public MasterListPresenter(MasterListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void requestData() {
        view.showWaiting();
        api.masterList(currentPage, AppConfig.PAGE_SIZE, new ActionCallbackListener<BaseListModel<ArrayList<Master>>>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                KLog.d(getClass().getSimpleName(),"bytesWritten="+bytesWritten+"totalSize="+totalSize);
            }

            @Override
            public void onSuccess(BaseListModel<ArrayList<Master>> data) {
                view.closeWait();
                if (data.getPageNum()==currentPage){
                    //关闭记载更多
                }
                ArrayList<Master> dataList = data.getList();
                if (dataList != null && dataList.size()>0) {
                    if (currentPage == 1){
                        view.refreshData(dataList);
                    }else {
                        view.loadMore(dataList);
                    }
                }else {
                    if (currentPage == 1){
                        view.showEmpty();
                    }else {
                        view.showTip("没有更多了");
                    }
                }
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
        currentPage ++;
        requestData();
    }
}
