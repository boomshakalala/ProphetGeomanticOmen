package com.xianzhifengshui.ui.setting;

import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.SPUtils;

import cn.jpush.im.android.api.JMessageClient;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 设置界面控制器
 */
public class SettingPresenter extends BasePresenter implements SettingContract.Presenter{

    SettingContract.View view;

    public SettingPresenter(SettingContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void logout(SPUtils sp) {
        JMessageClient.logout();
        sp.clear();
        view.logoutSuccess();
    }
}
