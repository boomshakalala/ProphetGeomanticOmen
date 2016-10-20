package com.xianzhifengshui.ui.index.mine;

import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.SPUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/13.
 * 描述: 个人中心页面控制器
 */
public class MinePresenter extends BasePresenter implements MineContract.Presenter{
    MineContract.View view;

    public MinePresenter(MineContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void checkIsLogin(SPUtils sp,boolean needUpdateUI,boolean needJump,int opt) {
        boolean isLogin = sp.getBoolean("isLogin");
        if (isLogin){
            if (needUpdateUI)
                view.showLoginInfo();
            if (needJump){
                view.jumpToActivity(opt);
            }
        }else {
            if (needUpdateUI)
                view.showDefaultInfo();
            if (needJump)
                view.toLoginActivity(opt);
        }
    }

    @Override
    public void checkIsLoginUpdateUI(SPUtils sp) {
        checkIsLogin(sp,true,false,0);
    }

    @Override
    public void checkIsLoginJump(SPUtils sp, int opt) {
        checkIsLogin(sp,false,true,opt);
    }
}
