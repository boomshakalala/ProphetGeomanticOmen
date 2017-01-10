package com.xianzhifengshui.ui.initiatetopic;

import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.api.model.TopicType;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.io.File;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/14.
 * 描述: 发表话题页mvp接口
 */
public interface InitiateTopicContract {
    interface View extends IView<Presenter>{
        void loadTopicTypes(List<TopicType> data);
        void loadTagStr(List<String> tagStr);
        void showError();
        void showEmpty();
    }

    interface Presenter extends IPresenter{
        void topicIssueConfirm(String title,String content,String typeCode,List<String> picList);
        void getTopicTypes();
    }
}
