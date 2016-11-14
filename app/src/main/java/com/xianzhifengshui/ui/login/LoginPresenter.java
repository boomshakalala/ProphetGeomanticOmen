package com.xianzhifengshui.ui.login;

import android.content.Context;

import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.SPUtils;
import com.xianzhifengshui.utils.StringUtils;

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
    public void login(String userName, String password) {
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
                saveLoginInfo(data);
                view.closeWait();
                view.showLoginSuccess("登录成功");

            }

            @Override
            public void onFailure(int errorEvent, String message) {

                view.closeWait();
                view.showLoginFalure(message);
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
