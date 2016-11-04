package com.xianzhifengshui.base;

import android.app.Application;

import com.xianzhifengshui.utils.KLog;

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
    }
}
