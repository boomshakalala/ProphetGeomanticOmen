package com.xianzhifengshui.ui.modifypassword;

import android.content.Context;

import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.StringUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 修改密码页面控制器
 */
public class ModifyPasswordPresenter extends BasePresenter implements ModifyPasswordContract.Presenter{
    private ModifyPasswordContract.View view;

    public ModifyPasswordPresenter(ModifyPasswordContract.View view) {
        this.view = view;
    }


    @Override
    public void updatePassword(String oldPassword, String newPassword) {
        if (isLogin()){
            String mobilePhone = getMobilePhone();
            if (StringUtils.isEmpty(oldPassword)){
                view.showTip("请输入原密码");
                return;
            }else  if (StringUtils.isEmpty(newPassword)){
                view.showTip("请输入新密码");
                return;
            }
            view.showWaiting();
            api.userUpdatePassword(mobilePhone, oldPassword, newPassword, new ActionCallbackListener<Void>() {
                @Override
                public void onProgress(long bytesWritten, long totalSize) {

                }

                @Override
                public void onSuccess(Void data) {
                    view.closeWait();
                    view.showUpdatetSuccess();
                }

                @Override
                public void onFailure(int errorEvent, String message) {
                    view.closeWait();
                    view.showTip(message);
                }
            });
        }else {
            view.toLoginActivity((Context)view);
        }
    }
}
