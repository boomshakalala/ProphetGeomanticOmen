package com.xianzhifengshui.ui.masterdetail;

import android.content.Context;

import com.xianzhifengshui.api.model.MasterDetailModel;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.widget.EmptyLayout;

/**
 * 作者: chengx
 * 日期: 2016/11/1.
 * 描述: 大师详情页面控制器
 */
public class MasterDetailPresenter extends BasePresenter implements MasterDetailContract.Presenter{

    private MasterDetailContract.View view;

    public MasterDetailPresenter(MasterDetailContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void requestData() {
        view.showWaiting();
        api.masterDetail("bd35193472fa43d6b6aa7cdcf96d4c33", "9e41413d569b4f8f89ce70f572842917", new ActionCallbackListener<MasterDetailModel>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(MasterDetailModel data) {
                view.showInfo(data);
                view.showService(data.getServiceType());
                view.showAboutMaster(data.getDesc());
                view.showEvaluate(data.getEvaluateList());
                view.showArticle(data.getArticleList());
                if (data.getEvaluateList().size()< AppConfig.PAGE_SIZE){
                    view.closeMoreEvaluate();
                }
                view.closeWait();
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.showTip(message);
                view.closeWait();
            }
        });
    }

    @Override
    public void collectMaster(String masterCode) {
        collectRequest(masterCode,1);
    }

    @Override
    public void unCollectMaster(String masterCode) {
        collectRequest(masterCode,0);
    }

    private void collectRequest(String masterCode, final int type){
        if (sp.getBoolean(AppConfig.IS_LOGIN)){
            String userCode = getUserCode();
            view.showWaiting();
            api.masterCollectionConfirm(masterCode, userCode, type, new ActionCallbackListener<Void>() {
                @Override
                public void onProgress(long bytesWritten, long totalSize) {

                }

                @Override
                public void onSuccess(Void data) {
                    view.closeWait();
                    if (type == 1)
                        view.showCollect();
                    else
                        view.showUnCollect();
                }

                @Override
                public void onFailure(int errorEvent, String message) {
                    view.showTip(message);
                    view.closeWait();
                }
            });
        }else {
            view.toLoginActivity((Context)view);
        }
    }
}
