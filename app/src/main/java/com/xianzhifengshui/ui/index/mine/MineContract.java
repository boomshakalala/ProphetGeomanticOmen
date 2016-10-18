package com.xianzhifengshui.ui.index.mine;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;
import com.xianzhifengshui.utils.SPUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/13.
 * 描述: 个人中心mvp接口
 */
public interface MineContract {
    interface View extends IView<Presenter>{
        void toLoginActivity(int id);
        void showLoginInfo();
        void showDefaultInfo();
        void jumpToActivity(int id);
    }

    interface Presenter extends IPresenter{
        void checkIsLogin(SPUtils sp,boolean needUpdateUI,boolean needJump,int opt);
        void checkIsLoginUpdateUI(SPUtils sp);
        void checkIsLoginJump(SPUtils sp,int opt);
    }
}
