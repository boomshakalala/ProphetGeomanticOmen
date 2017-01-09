package com.xianzhifengshui.ui.initiatetopic;

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

    }

    interface Presenter extends IPresenter{
        void uploadFiles(List<String> files);
    }
}
