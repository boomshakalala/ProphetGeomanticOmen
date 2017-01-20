package com.xianzhifengshui.ui.modifypassword;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 修改密码页mvp接口
 */
public interface ModifyPasswordContract{
    interface View extends IView<Presenter>{
        void showUpdatetSuccess();
    }

    interface Presenter extends IPresenter{
        void updatePassword(String oldPassword,String newPassword);
    }
}
