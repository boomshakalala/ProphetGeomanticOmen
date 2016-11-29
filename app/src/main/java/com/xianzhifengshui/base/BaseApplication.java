package com.xianzhifengshui.base;

import android.app.Application;

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

    @Override
    public void onCreate() {
        super.onCreate();
        //日志开关
        KLog.init(AppConfig.isDebug);
        JPushInterface.setDebugMode(true); 	// 设置开启日志
        JMessageClient.init(this);// 初始化 JMessage
        JMessageClient.setNotificationMode(JMessageClient.NOTI_MODE_NO_NOTIFICATION);
        FileUtils.createOrExistsDir(SDCardUtils.getSDCardPath() + File.separator + AppConfig.APP_FILE_PATH + File.separator + AppConfig.APP_PIC_PATH);
    }

}
