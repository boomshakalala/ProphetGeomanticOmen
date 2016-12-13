package com.xianzhifengshui.base;

import android.app.Application;
import android.content.Context;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.xianzhifengshui.ui.chat.ChatActivity;
import com.xianzhifengshui.utils.FileUtils;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.SDCardUtils;

import java.io.File;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.NotificationClickEvent;

/**
 * 作者: chengx
 * 日期: 2016/9/27.
 * 描述: 自定义应用入口
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        //日志开关
        instance = this;
        KLog.init(AppConfig.isDebug);
        JPushInterface.setDebugMode(true); 	// 设置开启日志
        JMessageClient.init(this);// 初始化 JMessage
        JMessageClient.setNotificationMode(JMessageClient.NOTI_MODE_DEFAULT);
        FileUtils.createOrExistsDir(AppConfig.APP_PIC_PATH);
        FileUtils.createOrExistsDir(AppConfig.APP_FILE_PATH);
        FileUtils.createOrExistsDir(AppConfig.APP_VOICE_PATH);
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                KLog.d("app", " onViewInitFinished is " + b);
            }
        };
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                KLog.d("app","onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                KLog.d("app","onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                KLog.d("app","onDownloadProgress:"+i);
            }
        });
        QbSdk.initX5Environment(getAppContext(),cb);
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }

}
