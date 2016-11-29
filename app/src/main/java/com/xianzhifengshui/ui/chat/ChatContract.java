package com.xianzhifengshui.ui.chat;

import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.util.List;

import cn.jpush.im.android.api.model.Message;

/**
 * 作者: chengx
 * 日期: 2016/11/29.
 * 描述:
 */
public interface ChatContract {
    interface View extends IView{
        void loadHistory(List<Message> data);
        void loadMessage(Message data);
    }

    interface Presenter extends IPresenter{
        void init();
        void destroy();
        void sendImageMessage(String userName,String imagePath);
        void sendTextMessage(String userName,String content);
        void sendVoiceMessage(String userName,String voicePath,int duration);
    }

}
