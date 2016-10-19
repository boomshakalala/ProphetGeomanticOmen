package com.xianzhifengshui.ui.feed;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 用户反馈mvp接口
 */
public interface FeedContract {
    interface View extends IView<Presenter>{

    }
    interface Presenter extends IPresenter{

    }
}
