package com.xianzhifengshui.wbapi;

import android.content.Context;

import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.xianzhifengshui.base.AppConfig;

/**
 * 作者：chengx
 * 日期：2016/12/21
 * 描述：
 */

public class WBAPI {

    public static void share(Context context){
        IWeiboShareAPI api = WeiboShareSDK.createWeiboAPI(context, AppConfig.WB_APP_ID);
        api.registerApp();
        WeiboMultiMessage msg = new WeiboMultiMessage();

    }

    public static void login(){

    }

}
