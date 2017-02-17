package com.xianzhifengshui.ui.login;

import android.content.Context;

import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.api.model.WXApiResponse;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.ui.chat.ChatContract;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.SPUtils;
import com.xianzhifengshui.utils.StringUtils;
import com.xianzhifengshui.wxapi.WXAPI;


import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import de.greenrobot.event.EventBus;

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
        EventBus.getDefault().register(this);

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
                loginChatService(data.getUserCode(), data.getUserCode());
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
        KLog.d(sp.getObject("user", null));

    }

    public void onEventMainThread(BaseResp resp){
        view.showWaiting();
        SendAuth.Resp authResp = (SendAuth.Resp)resp;
        String code = authResp.code;
        KLog.d(TAG,"code="+code);
        api.wxGetAccessToken(AppConfig.WX_APP_ID, AppConfig.WX_APP_SECRET, code, new ActionCallbackListener<WXApiResponse>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(WXApiResponse data) {
                final String openid = data.getOpenid();
                final String accessToken = data.getAccess_token();
                api.wxGetUserInfo(accessToken, openid, new ActionCallbackListener<WXApiResponse>() {
                    @Override
                    public void onProgress(long bytesWritten, long totalSize) {

                    }

                    @Override
                    public void onSuccess(WXApiResponse data) {
                        data.setAccess_token(accessToken);
                        data.setOpenid(openid);
                        api.userThirdLogin(data.getAccess_token(), "", "W", new ActionCallbackListener<User>() {
                            @Override
                            public void onProgress(long bytesWritten, long totalSize) {

                            }

                            @Override
                            public void onSuccess(User data) {
                                log(data);
                            }

                            @Override
                            public void onFailure(int errorEvent, String message) {
                                view.closeWait();
                            }
                        });
                    }

                    @Override
                    public void onFailure(int errorEvent, String message) {
                        view.closeWait();
                        view.showTip(message);
                    }
                });
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.closeWait();
                view.showTip(message);
            }
        });
    }

    @Override
    public void unRegisterEvent(){
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
