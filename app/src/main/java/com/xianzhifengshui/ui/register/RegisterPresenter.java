package com.xianzhifengshui.ui.register;

import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者: chengx
 * 日期: 2016/11/21.
 * 描述:
 */
public class RegisterPresenter extends BasePresenter implements RegisterContract.Presenter {

    RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void register(String phoneNum, String password, String nickName, String verifyCode) {
        view.showWaiting();
        api.userSaveUserInfo(phoneNum, password, nickName, new ActionCallbackListener<Void>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(Void data) {
                view.showTip("注册成功");
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.showTip(message);
            }
        });
    }

    @Override
    public void getVerifyCode(String phoneNum) {

    }
}
