package com.xianzhifengshui.ui.billdetail;

import com.xianzhifengshui.api.model.Bill;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public class BillDetailContract {
    interface View extends IView<Presenter> {
        void refreshData(Bill bill);
        void showFailure(String message);
    }

    interface Presenter extends IPresenter{
        void refreshData();
    }
}
