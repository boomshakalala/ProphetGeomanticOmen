package com.xianzhifengshui.ui.login;

import android.content.Context;

import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.SPUtils;
import com.xianzhifengshui.utils.StringUtils;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
 * 作者: chengx
 * 日期: 2016/10/9.
 * 描述: 登录页面控制器
 */
public class LoginPresenter extends BasePresenter implements LoginContract.Presenter {
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        super();
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void login(final String userName, final String password) {
        if (StringUtils.isEmpty(userName)){
            view.showTip("请输入用户名");
            return;
        }else if (StringUtils.isEmpty(password)){
            view.showTip("请输入密码");
            return;
        }
        if (!view.isActive()){
            return;
        }
        view.showWaiting();
        api.userLogin(userName, password, new ActionCallbackListener<User>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(User data) {
                loginChatService("admin", "123456");
                data.setPassword(password);
                saveLoginInfo(data);
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.closeWait();
                view.showLoginFalure(message);
            }
        });
    }

    private void loginChatService(String userName, String password) {
        JMessageClient.login(userName, password, new BasicCallback() {
            @Override
            public void gotResult(int code, String info) {
                KLog.d(TAG,"resultCode="+code+"responseInfo="+info);
                if (code != 0){
                    view.showTip("登录聊天服务器失败！");
                    ((BaseActivity)view).sp.clear();
                }else {
                    view.showLoginSuccess("登录成功");
                }
                view.closeWait();
            }
        });
    }


    public void saveLoginInfo(User user) {
        SPUtils sp = new SPUtils((Context)view, AppConfig.SP_NAME);
        sp.putBoolean("isLogin",true);
        sp.putObject("user", user);
        KLog.d(sp.getObject("user",null));

    }
}
