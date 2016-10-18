package com.xianzhifengshui.ui.edituserinfo;

import android.os.Handler;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 编辑用户信息控制器
 */
public class EditUserInfoPresenter extends BasePresenter implements EditUserInfoContract.Presenter{
    EditUserInfoContract.View view;

    public EditUserInfoPresenter(EditUserInfoContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void submitUserInfo() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.updateUserInfo();
                view.closeWait();
            }
        },3000);
    }

    @Override
    public void submitUserAvatar() {
        view.showWaiting();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.updateUserAvatar();
                view.closeWait();
            }
        },3000);
    }
}
