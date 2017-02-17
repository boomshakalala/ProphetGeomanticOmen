package com.xianzhifengshui.ui.chat;

import android.content.Context;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.provider.CalendarContract;

import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.ConstUtils;
import com.xianzhifengshui.utils.FileUtils;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.widget.RecordVoiceButton;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * 作者: chengx
 * 日期: 2016/11/29.
 * 描述:
 */
class ChatPresenter extends BasePresenter implements ChatContract.Presenter,RecordVoiceButton.OnRecordListener,Handler.Callback{

    private ChatContract.View view;
    private String userName;
    private File recordAudioFile;
    private MediaRecorder recorder;
    private long startTime;
    private Handler uiHandler;
    private AudioLevelRunnable levelRunnable;
    private Conversation conversation;

    ChatPresenter(ChatContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    ChatPresenter(ChatContract.View view, String userName) {
        this(view);
        this.userName = userName;
    }

    @Override
    public void init() {
        JMessageClient.registerEventReceiver(this);
        JMessageClient.enterSingleConversation(userName);
        conversation = JMessageClient.getSingleConversation(userName);
        if (conversation == null){
            conversation = Conversation.createSingleConversation(userName);
        }
        List<Message> historyMsg = conversation.getAllMessage();
        if (historyMsg != null && historyMsg.size()>0) {
            view.loadHistory(historyMsg);
        }
        uiHandler = new Handler(this);

    }

    @Override
    public void destroy() {
        JMessageClient.unRegisterEventReceiver(this);
        JMessageClient.exitConversation();
    }

    @Override
    public void sendImageMessage(String imagePath) {
        try {
            Message msg = JMessageClient.createSingleImageMessage(userName, new File(imagePath));
            msg.setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                   log("响应吗：" + i + "反馈信息：" + s);
                }
            });
            JMessageClient.sendMessage(msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTextMessage(String content) {
        TextContent textContent = new TextContent(content);
        if (conversation == null){
            KLog.d(TAG,"conversation=null");
            return;
        }
        Message msg = conversation.createSendMessage(textContent);
        view.loadMessage(msg);
        msg.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                KLog.d(TAG,"statusCode="+i+"statusInfo="+s);
            }
        });
        JMessageClient.sendMessage(msg);
    }

    private void sendVoiceMessage(int duration) {
        try {
            VoiceContent voiceContent = new VoiceContent(recordAudioFile,duration);
            Message msg = conversation.createSendMessage(voiceContent);
            msg.setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    KLog.d(TAG,"响应吗："+i+"相应信息:"+s);
                }
            });
            JMessageClient.sendMessage(msg);
            view.loadMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onEventMainThread(MessageEvent event){
        Message msg = event.getMessage();
        UserInfo targetInfo = (UserInfo) msg.getTargetInfo();
        if (targetInfo.getUserName().equals(userName))
        view.loadMessage(msg);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
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
            recorder.start();
            levelRunnable = new AudioLevelRunnable();
            threadPool.execute(levelRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRecording(){
        if (levelRunnable != null) {
            levelRunnable.exit();
        }
        try {
            recorder.stop();
        }catch (Exception e){
            KLog.d(TAG,"Catch exception: stop recorder failed!");
        }finally {
            recorder.release();
            recorder = null;
        }
    }

    @Override
    public void onRecordComplete() {
        stopRecording();
        long interalTime = System.currentTimeMillis() - startTime;
        if (interalTime < ConstUtils.SEC * 0.3){
            recordAudioFile.delete();
        }else {
            if (recordAudioFile !=null && recordAudioFile.exists()){
                MediaPlayer mp = new MediaPlayer();
                try {
                    FileInputStream fis = new FileInputStream(recordAudioFile);
                    mp.setDataSource(fis.getFD());
                    mp.prepare();
                }catch (IOException e){
                    e.printStackTrace();
                }
                int duration = mp.getDuration()/ConstUtils.SEC;
                if (duration<1)
                    duration = 1;
                else if (duration>60){
                    duration = 60;
                }
                sendVoiceMessage(duration);
                mp.release();
            }
        }

    }

    @Override
    public void onRecordCancel() {
        stopRecording();
        if (recordAudioFile != null) {
            recordAudioFile.delete();
        }
    }

    @Override
    public boolean handleMessage(android.os.Message msg) {
        view.updateVoiceLevel(msg.what);
        return false;
    }

    private class AudioLevelRunnable implements Runnable{

        private volatile boolean running = true;

        void exit(){
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
                try {
                    assert recorder != null;
                    int x = recorder.getMaxAmplitude();
                    if (x!=0) {
                        int f = (int) (10 * Math.log(x) / Math.log(10));
                        if (f < 20) {
                            uiHandler.sendEmptyMessage(0);
                        } else if (f < 26) {
                            uiHandler.sendEmptyMessage(1);
                        } else if (f < 32) {
                            uiHandler.sendEmptyMessage(2);
                        } else if (f < 38) {
                            uiHandler.sendEmptyMessage(3);
                        } else {
                            uiHandler.sendEmptyMessage(4);
                        }
                    }
                }catch (Exception e){
                        e.printStackTrace();
                }


            }

        }
    }

}
