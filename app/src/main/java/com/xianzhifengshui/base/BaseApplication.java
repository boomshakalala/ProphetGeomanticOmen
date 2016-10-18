package com.xianzhifengshui.base;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.XImageLoader;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * 作者: chengx
 * 日期: 2016/9/27.
 * 描述: 自定义应用入口
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化XImageLoader
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        XImageLoader.getInstance().init(configuration);
        //日志开关
        KLog.init(AppConfig.isDebug);

    }
}
