package com.xianzhifengshui.ui.edituserinfo;

import android.view.ViewOutlineProvider;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 编辑用户信息mvp接口
 */
public interface EditUserInfoContract {

    interface View extends IView<Presenter>{
        void updateUserInfo();
        void updateUserAvatar();
    }

    interface Presenter extends IPresenter{
        void submitUserInfo();
        void submitUserAvatar();
    }


}
