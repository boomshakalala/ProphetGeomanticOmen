package com.xianzhifengshui.ui.recharge;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述:
 */
public interface RechargeContract  {
    interface View extends IView<Presenter>{

    }

    interface Presenter extends IPresenter{
        void pay(int payMethod);
    }
}
