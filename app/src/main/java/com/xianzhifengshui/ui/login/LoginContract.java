package com.xianzhifengshui.ui.login;

import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: chengx
 * 日期: 2016/10/9.
 * 描述: 登录页面mvp接口
 */
public interface LoginContract {
    interface View extends IView<Presenter> {
        void showLoginSuccess(String message);
        void showLoginFalure(String message);
    }
    interface  Presenter extends IPresenter {
        /**
         * 用户登录
         * @param userName 用户名
         * @param password 密码
         */
        void login(String userName,String password);

    }
}
