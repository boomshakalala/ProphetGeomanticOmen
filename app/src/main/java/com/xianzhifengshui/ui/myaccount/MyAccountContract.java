package com.xianzhifengshui.ui.myaccount;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述: 我的账户页mvp接口
 */
public interface MyAccountContract {
    interface View extends IView<Presenter>{
        void showAccountInfo();
    }

    interface Presenter extends IPresenter{
        void requestAccountInfo();
    }
}
