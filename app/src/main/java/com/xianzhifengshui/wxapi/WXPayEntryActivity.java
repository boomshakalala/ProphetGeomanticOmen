package com.xianzhifengshui.wxapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xianzhifengshui.R;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.utils.KLog;

import java.util.IllegalFormatCodePointException;

/**
 * 作者: chengx
 * 日期: 2016/11/4.
 * 描述: 微信支付返回结果页
 */
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(this, AppConfig.WX_APP_ID,true);
        api.handleIntent(this.getIntent(), this);
    }

    @Override
    protected int getContentLayoutId() {
        return -1;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        log("微信支付回调>>resp>>" + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX){
            if (resp.errCode == 0){
                showToast(getString(R.string.text_pay_success));
            }else {
                showToast(getString(R.string.text_pay_failure));
            }
            finish();
        }
    }
}
