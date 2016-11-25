package com.xianzhifengshui.base;

import android.app.Application;

import com.xianzhifengshui.utils.FileUtils;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.SDCardUtils;

import java.io.File;

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
        FileUtils.createOrExistsDir(SDCardUtils.getSDCardPath() + File.separator + AppConfig.APP_FILE_PATH + File.separator + AppConfig.APP_PIC_PATH);
    }
}
