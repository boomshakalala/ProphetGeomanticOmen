package com.xianzhifengshui.ui.setting;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;
import com.xianzhifengshui.utils.SPUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 设置页面mvp接口
 */
public interface SettingContract {
    interface View extends IView<Presenter>{
        void logoutSuccess();
    }

    interface Presenter extends IPresenter{
        void logout(SPUtils sp);
    }
}
