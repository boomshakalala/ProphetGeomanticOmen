package com.xianzhifengshui.base;

import com.xianzhifengshui.utils.PhoneUtils;
import com.xianzhifengshui.utils.SDCardUtils;

import java.io.File;

/**
 * 作者: chengx
 * 日期: 2016/9/28.
 * 描述: 应用配置
 */
public class AppConfig {
    public static boolean isDebug = true;

    public static String SP_NAME = "config";

    public static int PAGE_SIZE = 10;

    public static int RESULT_LOGIN = 1;
    public static int RESULT_LOGOUT = -1;

    public static String WX_APP_ID = "";

    public static String WB_APP_ID = "";

    public static String QQ_APP_ID = "";

    public static String JPUSH_APPKEY="824e0cd5141e8db5b3ecacc1";

    public static String APP_PIC_PATH = SDCardUtils.getSDCardPath() + File.separator + "先知风水" + File.separator + "images" + File.separator;

    public static String APP_FILE_PATH = SDCardUtils.getSDCardPath() + File.separator + "先知风水" + File.separator + "files" + File.separator;

    public static String APP_VOICE_PATH = SDCardUtils.getSDCardPath() + File.separator + "先知风水" + File.separator + "voices" + File.separator;
}
