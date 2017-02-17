package com.xianzhifengshui.ui.pay;

import android.content.Context;

import com.xianzhifengshui.api.model.PayOrder;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.wxapi.WXAPI;

/**
 * 作者：chengx
 * 日期：2017/1/6
 * 描述：
 */

public class PayPresenter extends BasePresenter implements PayContract.Presenter {
    PayContract.View view;

    public PayPresenter(PayContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void pay() {
        api.lectureOrderPay(getUserCode(), "192.168.1.1", 1, "讲座报名", "fds", "W", new ActionCallbackListener<PayOrder>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(PayOrder data) {
                log(data);
                WXAPI.pay((Context)view,data.getPrepayId(),data.getSign());
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                log(message);
            } 
        });
    }
}
