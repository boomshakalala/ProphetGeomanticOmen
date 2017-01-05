package com.xianzhifengshui.ui.lecturedetail;

import android.content.Context;

import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者: chengx
 * 日期: 2016/11/3.
 * 描述:
 */
public class LectureDetailPresenter extends BasePresenter implements LectureDetailContract.Presenter {
    private LectureDetailContract.View view;

    public LectureDetailPresenter(LectureDetailContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void requestData(String lecturesCode, String userCode) {
        api.lecturesDetail(lecturesCode, userCode, new ActionCallbackListener<Lecture>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(Lecture data) {
                view.loadData(data);
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                log("errorEvent="+errorEvent);
                log("message="+message);
                view.showFailure();
                view.showTip(message);
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

    private void collectRequest(String lectureCode, final int type){
        if (sp.getBoolean(AppConfig.IS_LOGIN)){
            String userCode = getUserCode();
            view.showWaiting();
            api.lectureCollectionCollect(userCode, lectureCode, type, new ActionCallbackListener<Void>() {
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
