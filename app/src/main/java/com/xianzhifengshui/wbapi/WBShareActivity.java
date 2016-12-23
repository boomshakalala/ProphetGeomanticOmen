package com.xianzhifengshui.wbapi;

import android.app.Activity;
import android.os.Bundle;

import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者：chengx
 * 日期：2016/12/21
 * 描述：
 */

public class WBShareActivity extends Activity {
    IWeiboShareAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WeiboShareSDK.createWeiboAPI(this, AppConfig.WB_APP_ID);

    }
}
