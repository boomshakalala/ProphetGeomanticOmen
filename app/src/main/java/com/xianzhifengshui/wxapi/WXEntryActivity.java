package com.xianzhifengshui.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xianzhifengshui.R;
import com.xianzhifengshui.api.net.HttpEngine;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.SPUtils;
import com.xianzhifengshui.utils.ToastUtils;

import de.greenrobot.event.EventBus;


/**
 * 作者: chengx
 * 日期: 2016/11/4.
 * 描述:
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static String TAG = "WXEntryActivity";
    private IWXAPI api;
    private SPUtils sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    protected void initViews() {

    }

    protected void initData() {
        api = WXAPIFactory.createWXAPI(this, AppConfig.WX_APP_ID,true);
        api.handleIntent(this.getIntent(), this);
        sp = new SPUtils(this,AppConfig.SP_NAME);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }




    @Override
    public void onReq(BaseReq baseReq) {
        KLog.d(TAG,"resp=========>"+baseReq);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.errCode == BaseResp.ErrCode.ERR_OK){
            if (baseResp instanceof SendAuth.Resp){
                EventBus.getDefault().post(baseResp);
            }else {
                ToastUtils.showToast(this,"分享成功");
            }
            finish();
        }else if (baseResp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL ||baseResp.errCode == BaseResp.ErrCode.ERR_AUTH_DENIED){
            ToastUtils.showToast(this,"玩儿呢哥？");
            finish();
        }else {
            ToastUtils.showToast(this,"操作失败");
            finish();
        }
    }

    private void getToken(String code) {
        KLog.d(TAG,code);

//        HttpEngine.getInstance()
    }
}
