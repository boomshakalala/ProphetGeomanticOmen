package com.xianzhifengshui.base;

import android.app.Application;

import com.xianzhifengshui.api.Api;
import com.xianzhifengshui.api.ApiImpl;
import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.SPUtils;
import com.xianzhifengshui.utils.ThreadPoolUtils;

/**
 * 作者: chengx
 * 日期: 2016/9/29.
 * 描述: Presenter基类
 */
public class BasePresenter {
    protected final String TAG = getClass().getSimpleName();
    protected Api api;
    protected ThreadPoolUtils threadPool;
    protected SPUtils sp;

    public BasePresenter() {
        this.api = ApiImpl.getInstance();
        this.threadPool = ThreadPoolUtils.getInstance(ThreadPoolUtils.Type.FixedThread,5);
        sp = new SPUtils(BaseApplication.getAppContext(),AppConfig.SP_NAME);
    }

    /**
     * 打印普通log日志
     * @param objects 日志内容
     */
    public void log(Object... objects){
        KLog.d(TAG, objects);
    }

    public User getUserInfo(){
        return sp.getObject("user",null);
    }

    public String getUserCode(){
        User user = getUserInfo();
        if (user!=null){
            return user.getBizCode();
        }else {
            return "";
        }
    }
}
