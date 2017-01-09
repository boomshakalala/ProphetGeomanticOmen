package com.xianzhifengshui.wxapi;

import android.content.Context;
import android.provider.Settings;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.utils.StringUtils;

/**
 * 作者: chengx
 * 日期: 2016/11/4.
 * 描述: 微信功能
 */
public class WXAPI {


    /**
     * 微信支付
     * @param context 上下文
     * @param prepayId 预支付交易回话ID
     * @param sign 签名
     */
    public static void pay(Context context, String prepayId,String sign){
        IWXAPI api = WXAPIFactory.createWXAPI(context,AppConfig.WX_APP_ID,false);
        api.registerApp(AppConfig.WX_APP_ID);
        PayReq req = new PayReq();
        req.appId = AppConfig.WX_APP_ID;  // 测试用appId
        req.partnerId = AppConfig.WX_PARTNER_ID;
        req.prepayId = prepayId;
        req.nonceStr = StringUtils.getRandomString(32);
        req.timeStamp = System.currentTimeMillis()/1000+"";
        req.packageValue = "Sign=WXPay";
        req.sign = sign;
        api.sendReq(req);
    }

    public static void share(){
        //TODO:微信分享
    }

    public static void login(Context context){
        IWXAPI api = WXAPIFactory.createWXAPI(context,AppConfig.WX_APP_ID);
        api.registerApp(AppConfig.WX_APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

}
