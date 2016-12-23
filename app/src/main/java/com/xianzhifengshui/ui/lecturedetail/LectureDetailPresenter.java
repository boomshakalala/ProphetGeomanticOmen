package com.xianzhifengshui.ui.lecturedetail;

import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.net.ActionCallbackListener;
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
                log(data);
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                log("errorEvent="+errorEvent);
                log("message="+message);
            }
        });
    }
}
