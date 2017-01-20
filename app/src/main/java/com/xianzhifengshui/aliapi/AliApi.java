package com.xianzhifengshui.aliapi;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;

/**
 * 作者：chengx
 * 日期：2017/1/16
 * 描述：
 */

public class AliApi {
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

        }
    };
    public static void pay(final Activity context, String orderInfo){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
    }

}
