package com.xianzhifengshui.wxapi;

import android.content.Intent;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/11/4.
 * 描述:
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        api = WXAPIFactory.createWXAPI(this, AppConfig.WX_APP_ID);
        api.handleIntent(getIntent(),this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
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
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

    }
}
