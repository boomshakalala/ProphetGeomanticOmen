package com.xianzhifengshui.ui.pay;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者：chengx
 * 日期：2017/1/6
 * 描述：
 */

public interface PayContract {
    interface View extends IView<Presenter>{
        void loadData();
    }

    interface Presenter extends IPresenter{
        void refreshData();
        void pay();
    }


}
