package com.xianzhifengshui.ui.resetpassword;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者：chengx
 * 日期：2017/1/10
 * 描述：
 */

public class ResetPasswordContract {
    interface View extends IView<Presenter> {
        void showResetSuccess();
        void setTimeCount(String timeCount);
        void setClickble(boolean clickble);
    }

    interface Presenter extends IPresenter{
        void resetPassword(String phoneNum,String newPassword,String verifyCode);
        void getVerifyCode(String phoneNum);
    }
}
