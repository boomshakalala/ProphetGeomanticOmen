package com.xianzhifengshui.ui.chat;

import android.provider.CalendarContract;

import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.KLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

/**
 * 作者: chengx
 * 日期: 2016/11/29.
 * 描述:
 */
public class ChatPresenter extends BasePresenter implements ChatContract.Presenter{

    ChatContract.View view;
    String userName;

    public ChatPresenter(ChatContract.View view) {
        this.view = view;
    }

    public ChatPresenter(ChatContract.View view,String userName) {
        this(view);
        this.userName = userName;
    }

    @Override
    public void init() {
        JMessageClient.registerEventReceiver(this);
        JMessageClient.enterSingleConversation(userName);
        Conversation conversation = JMessageClient.getSingleConversation(userName);
        if (conversation != null) {
            List<Message> historyMsg = conversation.getAllMessage();
            if (historyMsg != null && historyMsg.size()>0) {
                view.loadHistory(historyMsg);
            }
        }

    }

    @Override
    public void destroy() {
        JMessageClient.unRegisterEventReceiver(this);
        JMessageClient.exitConversation();
    }

    @Override
    public void sendImageMessage(String userName, String imagePath) {
        try {
            Message msg = JMessageClient.createSingleImageMessage(userName,new File(imagePath));
            msg.setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    KLog.d(TAG, "响应吗：" + i + "反馈信息：" + s);
                }
            });
            JMessageClient.sendMessage(msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTextMessage(String userName, String content) {
        Message msg = JMessageClient.createSingleTextMessage(userName, content);
        msg.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                KLog.d(TAG, "响应吗：" + i + "反馈信息：" + s);
            }
        });
        JMessageClient.sendMessage(msg);
    }

    @Override
    public void sendVoiceMessage(String userName, String voicePath, int duration) {
        try {
            Message msg = JMessageClient.createSingleVoiceMessage(userName,new File(voicePath),duration);
            msg.setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    KLog.d(TAG, "响应吗：" + i + "反馈信息：" + s);
                }
            });
            JMessageClient.sendMessage(msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onEventMainThread(MessageEvent event){
        Message msg = event.getMessage();
        view.loadMessage(msg);
    }
}
