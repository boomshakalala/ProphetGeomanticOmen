package com.xianzhifengshui.base;

import android.app.Application;

import com.xianzhifengshui.utils.FileUtils;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.SDCardUtils;

import java.io.File;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者: chengx
 * 日期: 2016/9/27.
 * 描述: 自定义应用入口
 */
public class BaseApplication extends Application {

    public static String xianzhiFilePath;

    @Override
    public void onCreate() {
        super.onCreate();
        //日志开关
        KLog.init(AppConfig.isDebug);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        FileUtils.createOrExistsDir(SDCardUtils.getSDCardPath() + File.separator + AppConfig.APP_FILE_PATH + File.separator + AppConfig.APP_PIC_PATH);
    }
}
