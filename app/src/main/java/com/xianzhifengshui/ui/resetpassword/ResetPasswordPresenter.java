package com.xianzhifengshui.ui.resetpassword;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者：chengx
 * 日期：2017/1/10
 * 描述：
 */

public class ResetPasswordPresenter extends BasePresenter implements ResetPasswordContract.Presenter {
    ResetPasswordContract.View view;

    public ResetPasswordPresenter(ResetPasswordContract.View view) {
        this.view = view;
    }
}
