package com.xianzhifengshui.ui.register;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: 陈冠希
 * 日期: 2016/10/9.
 * 描述: 登录页面mvp接口
 */
public interface RegisterContract {
    interface View extends IView<Presenter> {
        void showRegisterSuccess(String message);
        void showRegisterFalure(String message);
    }
    interface  Presenter extends IPresenter {
        /**
         * 用户注册
         * @param phoneNum 手机号
         * @param password 密码
         * @param nickName 昵称
         * @param verifyCode 验证码
         */
        void register(String phoneNum, String password,String nickName,String verifyCode);

    }
}
