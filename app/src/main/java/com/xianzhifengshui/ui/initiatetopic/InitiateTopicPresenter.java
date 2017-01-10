package com.xianzhifengshui.ui.initiatetopic;

import com.xianzhifengshui.api.BaseListModel;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.api.model.TopicType;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.ImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/14.
 * 描述:
 */
public class InitiateTopicPresenter extends BasePresenter implements InitiateTopicContract.Presenter {
    private InitiateTopicContract.View view;

    public InitiateTopicPresenter(InitiateTopicContract.View view) {
        this.view = view;
    }

    @Override
    public void topicIssueConfirm(String title, String content, String typeCode, List<String> picList) {
        api.topicIssueConfirm(getUserCode(), title, content, typeCode, picList, new ActionCallbackListener<Void>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(Void data) {

            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.showTip(message);
            }
        });
    }

    @Override
    public void getTopicTypes() {
        view.showWaiting();
        api.topicTypeList(new ActionCallbackListener<BaseListModel<ArrayList<TopicType>>>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(BaseListModel<ArrayList<TopicType>> data) {
                List<TopicType> topicTypes = data.getList();
                view.loadTopicTypes(topicTypes);
                List<String> tagStr = new ArrayList<>();
                for (TopicType topicType : topicTypes) {
                    tagStr.add(topicType.getTitle());
                }
                view.loadTagStr(tagStr);
                view.closeWait();
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.showError();
            }
        });
    }


}
