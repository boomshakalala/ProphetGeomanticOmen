package com.xianzhifengshui.ui.chat;

import android.content.Context;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.provider.CalendarContract;

import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.FileUtils;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.widget.RecordVoiceButton;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class ChatPresenter extends BasePresenter implements ChatContract.Presenter,RecordVoiceButton.OnRecordListener{

    private ChatContract.View view;
    private String userName;
    private File recordAudioFile;
    private MediaRecorder recorder;
    private long startTime;

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

    private void sendVoiceMessage(String userName, String voicePath, int duration) {
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

    @Override
    public void onRecordStart() {
        try {
            recordAudioFile = new File(AppConfig.APP_VOICE_PATH+System.currentTimeMillis()+".amr");
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            recorder.setOutputFile(recordAudioFile.getAbsolutePath());
            recordAudioFile.createNewFile();
            recorder.prepare();
            startTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRecordComplete() {

    }

    @Override
    public void onRecordCancel() {

    }

    class getAudioLevelRunnable implements Runnable{

        private volatile boolean running = true;

        public void exit(){
            running = false;
        }

        @Override
        public void run() {
            while (running){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (recorder == null && !running) {
                    break;
                }
                int x = recorder.getMaxAmplitude();
                if (x!=0){

                }
            }

        }
    }
}
