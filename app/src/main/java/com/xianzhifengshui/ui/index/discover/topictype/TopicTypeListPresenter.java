package com.xianzhifengshui.ui.index.discover.topictype;

import android.os.Handler;

import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.api.model.TopicType;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/12.
 * 描述: 讲座列表页控制器
 */
public class TopicTypeListPresenter extends BasePresenter implements TopicTypeListContract.Presenter{

    private TopicTypeListContract.View view;

    public TopicTypeListPresenter(TopicTypeListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void requestData() {
        view.showWaiting();
        api.topicTypeList(new ActionCallbackListener<BaseListModel<ArrayList<TopicType>>>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                KLog.d(getClass().getSimpleName(),"bytesWritten="+bytesWritten+"totalSize="+totalSize);
            }

            @Override
            public void onSuccess(BaseListModel<ArrayList<TopicType>> data) {
                ArrayList<TopicType> dataList = data.getList();
                if (dataList != null && dataList.size()>0) {
                    view.refreshData(dataList);
                }else {
                    view.showEmpty();
                }
                view.closeWait();
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.closeWait();
                view.showFailure();
                KLog.d(getClass().getSimpleName(),message);
                view.showTip(message);
            }
        });
    }


    @Override
    public void refreshData() {
        requestData();
    }

    @Override
    public void loadMore() {

    }
}
